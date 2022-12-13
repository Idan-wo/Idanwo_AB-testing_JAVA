package com.ayotycoon.services.WSManager;

import com.ayotycoon.config.redis.RedisPubSubMessage;
import com.ayotycoon.entities.Cell;
import com.ayotycoon.services.AppService;
import com.ayotycoon.services.CONSTANTS;
import com.ayotycoon.utils.CellPair;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class LocalWSManager implements WSManager {
    private final AppService appService;
    private final StringRedisTemplate stringRedisTemplate;

    private final Map<String, Set<WebSocketSession>> keyToSessions = new HashMap<>();
    private final Map<WebSocketSession, Set<String>> sessionsToKeys = new HashMap<>();

    @Override
    public void putSessionAndKey(WebSocketSession session, String key) {
        putSessionAndKey(session,key,null);
    }
    @Override
    public void putSessionAndKey(WebSocketSession session, String key, String orgId) {
        if(appService.isOrgMode()) key = "%s|%s".formatted(orgId,key);
        if (!keyToSessions.containsKey(key) || !sessionsToKeys.containsKey(session)) {
            keyToSessions.put(key, new HashSet<>(10));
            sessionsToKeys.put(session, new HashSet<>(10));
        }
        keyToSessions.get(key).add(session);
        sessionsToKeys.get(session).add(key);
    }

    @Override
    public Set<WebSocketSession> getSessionsByKey(String key) {
        return getSessionsByKey(key, null);
    }
    @Override
    public Set<WebSocketSession> getSessionsByKey(String key, String orgId) {
        if(appService.isOrgMode()) key = "%s|%s".formatted(orgId,key);
        return keyToSessions.getOrDefault(key, new HashSet<>());
    }

    @Override
    public void removeSession(WebSocketSession session) {
        Set<String> keys = sessionsToKeys.get(session);
        sessionsToKeys.remove(session);
        if (keys == null || keys.isEmpty()) return;
        for (String key : keys) {
            Set<WebSocketSession> keySessions = keyToSessions.get(key);
            if (!keySessions.contains(session)) continue;
            keySessions.remove(session);
            if (keySessions.size() == 0) keySessions.remove(key);
        }
    }

    @Override
    public void broadcastKeyChanges(String key, Cell cell) throws Exception {
        stringRedisTemplate.convertAndSend(CONSTANTS.redisSubKey, new RedisPubSubMessage(appService.getId(), cell.getKey(), cell.getValue(), cell.getType(), cell.getOrgId().toString()).toString());
        log.info("Broadcasted message");
        //        for (WebSocketSession session : getSessionsByKey(key)) session.sendMessage(new TextMessage(cell.toPair().toJSON(cell.getType())));
    }

    @Override
    public void broadcastKeyChangesFromOtherServices(RedisPubSubMessage redisPubSubMessage) throws Exception {
        log.info("Received message", redisPubSubMessage.toString());
        for (WebSocketSession session : getSessionsByKey(redisPubSubMessage.getCellKey(), redisPubSubMessage.getOrgId()))
            session.sendMessage(new TextMessage(
                    new CellPair<>( redisPubSubMessage.getCellKey(),redisPubSubMessage.getCellValue()).toJSON(redisPubSubMessage.getCellType())
            ));
    }

    @Override
    public void logCurrent(org.slf4j.Logger log) {

        log.info("\n%s".formatted(keyToSessions.toString()));
    }
    @Override
    public Map<String, Set<String>> getSessions() {
        Map<String, Set<String>> m = new HashMap<>();

         for(String key: keyToSessions.keySet()){
             m.put(key, new HashSet<>());

            for(WebSocketSession socketSession: keyToSessions.get(key)){
                m.get(key).add(socketSession.getId());
            }
         }
         return m;
    }
}
