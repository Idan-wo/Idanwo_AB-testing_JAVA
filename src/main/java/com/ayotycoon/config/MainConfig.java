package com.ayotycoon.config;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig {
    @Value("${spring.sendgrid.api-key}")
    String sendGridAPIKey;

    public SendGrid Config() {
        return new SendGrid(sendGridAPIKey);
    }

}
