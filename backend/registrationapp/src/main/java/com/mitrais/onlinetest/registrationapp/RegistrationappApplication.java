package com.mitrais.onlinetest.registrationapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RegistrationappApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationappApplication.class, args);
	}

}
