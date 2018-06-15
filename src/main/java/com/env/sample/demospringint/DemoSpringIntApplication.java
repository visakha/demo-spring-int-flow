package com.env.sample.demospringint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration
public class DemoSpringIntApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringIntApplication.class, args);
	}
}
