package com.ayotycoon.bootstrap;


import com.ayotycoon.repositories.CellRepository;
import com.ayotycoon.services.AppService;
import com.ayotycoon.services.CONSTANTS;
import com.ayotycoon.services.WSManager.LocalWSManager;
import com.ayotycoon.services.WSManager.WSManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;


@Order(1)
@Slf4j
@RequiredArgsConstructor
@Component
public class AppBootstrap implements CommandLineRunner {
    private final AppService appService;
    private final WSManager wsManager;


    @Value("${redis.subkey:redis}")
    private  String redisSubKey;


    @Value("${user.authentication.header}")
    private String userHeaderName;
    @Value("${user.authentication.key}")
    private String userKey;
    @Value("${user.authentication.expiration}")
    private Long userExpirationTime;



    @Override
    public void run(String... args) throws Exception {

        log.info("[APP ID] " + appService.getId());
        CONSTANTS.userHeaderName = userHeaderName;
        CONSTANTS.userKey = userKey;
        CONSTANTS.userExpirationTime = userExpirationTime;
        CONSTANTS.wsManager = wsManager;
        CONSTANTS.redisSubKey = redisSubKey;

    }
    private void clearTest(){
        if(!appService.isEnvTest()) return;

    }


}

