package com.ameda.kisevu.works;
/*
*
@author ameda
@project java-security
*
*/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JavaSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaSecurityApplication.class,args);
    }

}
