package com.ayotycoon.utils;

import com.ayotycoon.entities.Cell;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;

public class WSManager {
    private static Map<String, Set<WebSocketSession>> keyToSessions = new HashMap<>();
    private static Map<WebSocketSession, Set<String>> sessionsToKeys = new HashMap<>();

    public static void putSessionAndKey(WebSocketSession session,String key){
        if(!keyToSessions.containsKey(key)){
            keyToSessions.put(key, new HashSet<>(10)) ;
            sessionsToKeys.put(session, new HashSet<>(10)) ;
        }

        keyToSessions.get(key).add(session);
        sessionsToKeys.get(session).add(key);
    }

    public static Set<WebSocketSession> getSessionsByKey(String key){
        return keyToSessions.get(key);
    }
    public static void removeSession(WebSocketSession session){
        Set<String> keys =  sessionsToKeys.get(session);
        sessionsToKeys.remove(session);
        if(keys == null || keys.isEmpty()) return;
        for(String key: keys){
            Set<WebSocketSession> keySessions = keyToSessions.get(key);
            if(!keySessions.contains(session))continue;
            keySessions.remove(session);
            if(keySessions.size() == 0) keySessions.remove(key);
        }
    }


    public static void broadcastKeyChanges(String key, Cell cell) throws Exception{
        for(WebSocketSession session:  getSessionsByKey(key)){
            session.sendMessage(new TextMessage(cell.toPair().toString()));
        }
    }

    public static void registerId() throws Exception{


    }
}
