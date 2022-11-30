package com.ayotycoon.bootstrap;


import com.ayotycoon.services.AppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Order(1)
@Slf4j
@RequiredArgsConstructor
@Component
public class AppBootstrap implements CommandLineRunner {
    private final AppService appService;
    @Override
    public void run(String... args) throws Exception {
        log.info("[APP ID] " + appService.getId());


    }


}

