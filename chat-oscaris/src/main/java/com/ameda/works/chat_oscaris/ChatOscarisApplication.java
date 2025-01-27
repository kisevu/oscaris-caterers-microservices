package com.ameda.works.chat_oscaris;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ChatOscarisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatOscarisApplication.class, args);
	}

}
