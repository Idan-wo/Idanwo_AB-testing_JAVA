package com.ayotycoon.config.redis;

import com.ayotycoon.services.CONSTANTS;
import com.ayotycoon.services.WSManager.WSManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;

@Slf4j
public class DefaultMessageDelegate implements MessageDelegate{

    @Override
    public void handleMessage(String message) {

    }

    @Override
    public void handleMessage(Map message) {

    }

    @Override
    public void handleMessage(byte[] message) {

    }

    @Override
    public void handleMessage(Serializable message) {

        try {
            CONSTANTS.wsManager.broadcastKeyChangesFromOtherServices(RedisPubSubMessage.fromString((String) message));
        }catch (Exception e){
log.error("error parsing msg", e);
        }

    }

    @Override
    public void handleMessage(Serializable message, String channel) {

    }


    public void handleMessage() {

    }
}
