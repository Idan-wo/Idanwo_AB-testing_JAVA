package com.ayotycoon.controllers;

import com.ayotycoon.utils.WSManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.List;

@Component
public class CellHandler extends AbstractWebSocketHandler {
    private static final ObjectMapper OM = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println(session);
       // System.out.println(OM.writeValueAsString(session));

    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        System.out.println(session);

        System.out.println(message.getPayload());

        List<String> keys = OM.readValue(message.getPayload().toString(), List.class);

        for (String key: keys){
            WSManager.putSessionAndKey(session, key);
        }

        session.sendMessage(new TextMessage(keys.size() +" Added"));
    }

}
