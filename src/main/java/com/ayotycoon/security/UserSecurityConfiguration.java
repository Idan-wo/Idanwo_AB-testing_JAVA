package com.ayotycoon.security;

import com.ayotycoon.services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;

@EnableWebSecurity
@Order(2)
public class UserSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    DataSource dataSource;
    @Autowired
    ApplicationUserDetailsService userDetailsService;
    @Autowired
    AppService appService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //  auth.jdbcAuthentication().dataSource(dataSource);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoderBean());
  }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

            http.csrf().and().csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/ping").permitAll()
                    .anyRequest().permitAll()
                    .and()
                    .addFilter(new UserAuthenticationFilter(authenticationManager()))
                    .addFilter(new UserAuthorizationFilter(authenticationManager()))
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ;

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }


    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }


}
