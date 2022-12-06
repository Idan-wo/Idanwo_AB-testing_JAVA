package com.ayotycoon.bootstrap;


import com.ayotycoon.services.AppService;
import com.ayotycoon.services.CONSTANTS;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Order(1)
@Slf4j
@RequiredArgsConstructor
@Component
public class AppBootstrap implements CommandLineRunner {
    private final AppService appService;


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


    }


}

