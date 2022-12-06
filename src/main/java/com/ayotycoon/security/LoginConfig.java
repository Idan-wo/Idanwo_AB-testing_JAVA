package com.ayotycoon.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
public class LoginConfig {
    @Bean
    public AuthenticationEntryPoint userLogin(){
        return new LoginUrlAuthenticationEntryPoint("/api/v1/organisation/login");
    }
}
