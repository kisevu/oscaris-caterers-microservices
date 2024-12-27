package com.oscaris.caterers.oscaris_service_api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OscarisServiceApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(OscarisServiceApiGatewayApplication.class, args);
	}

}
