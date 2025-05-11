package com.fintrack_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.fintrack_backend.model")
@EnableJpaRepositories(basePackages = "com.fintrack_backend.repository")
public class FintrackBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FintrackBackendApplication.class, args);
	}

}
