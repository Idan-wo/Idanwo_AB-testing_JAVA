package com.ayotycoon.services.WSManager;

import com.ayotycoon.config.redis.RedisPubSubMessage;
import com.ayotycoon.entities.Cell;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public interface WSManager {
    void putSessionAndKey(WebSocketSession session, String key);
    void putSessionAndKey(WebSocketSession session, String key, String orgId);
    Set<WebSocketSession> getSessionsByKey(String key);
    Set<WebSocketSession> getSessionsByKey(String key, String orgId);
    void removeSession(WebSocketSession session);
    void broadcastKeyChanges(String key, Cell cell) throws Exception;
    void logCurrent(org.slf4j.Logger log);
    void broadcastKeyChangesFromOtherServices(RedisPubSubMessage redisPubSubMessage) throws Exception;
    Map<String, Set<String>> getSessions();
}
