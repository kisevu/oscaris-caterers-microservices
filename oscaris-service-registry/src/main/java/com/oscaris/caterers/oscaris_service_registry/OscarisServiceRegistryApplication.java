package com.oscaris.caterers.oscaris_service_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class OscarisServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(OscarisServiceRegistryApplication.class, args);
	}

}
