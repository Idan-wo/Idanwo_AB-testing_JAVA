package com.ayotycoon.bootstrap;


import com.ayotycoon.repositories.CellRepository;
import com.ayotycoon.repositories.OrganisationRepository;
import com.ayotycoon.repositories.UserRepository;
import com.ayotycoon.services.AppService;
import com.ayotycoon.services.CONSTANTS;
import com.ayotycoon.services.WSManager.WSManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;


@Slf4j
@RequiredArgsConstructor
@Component
public class IntegrationBootstrap implements CommandLineRunner {
    private final AppService appService;
    private final CellRepository cellRepository;
    private final OrganisationRepository organisationRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if(!appService.isEnvTest()) return;
        cellRepository.deleteAll();
        log.info("Deleting all cells");
        organisationRepository.deleteAll();
        log.info("Deleting all orgs");
        userRepository.deleteAll();
        log.info("Deleting all users");
    }
}

