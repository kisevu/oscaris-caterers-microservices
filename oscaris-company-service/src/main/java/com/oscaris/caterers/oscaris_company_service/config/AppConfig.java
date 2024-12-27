package com.oscaris.caterers.oscaris_company_service.config;
/*
*
@author ameda
@project Oscaris-caterers
*
*/

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure().load();
    }
}
