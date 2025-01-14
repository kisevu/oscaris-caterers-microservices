package com.oscaris.caterers.oscaris_service_api_gateway.config;
/*
*
@author ameda
@project requisition
*
*/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception{
        return http.authorizeHttpRequests(
                authorize -> authorize.anyRequest()
                        .authenticated())
                .oauth2ResourceServer(
                        oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .build();
    }
}