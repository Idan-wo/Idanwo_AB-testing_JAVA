package com.ayotycoon.controllers;

import com.ayotycoon.services.AppService;
import com.ayotycoon.services.CONSTANTS;
import com.ayotycoon.utils.WSManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class CellWebSocketHandler extends AbstractWebSocketHandler {
    private final AppService appService;


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("[Session Connected] " + session.toString());
        WSManager.logCurrent(log);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("[Received Message] " + message.getPayload());
        try {
            List<String> keys = CONSTANTS.OM.readValue(message.getPayload().toString(), List.class);
            for (String key : keys) {
                WSManager.putSessionAndKey(session, key);
            }
        } catch (Exception e) {
            log.info("Could not parse message to list  " + e);
        }
        WSManager.logCurrent(log);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("[Session Closed] " + status + " " + session.toString());
        WSManager.removeSession(session);
        WSManager.logCurrent(log);
    }

}
