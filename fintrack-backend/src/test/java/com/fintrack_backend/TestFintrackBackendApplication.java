package com.fintrack_backend;

import org.springframework.boot.SpringApplication;

public class TestFintrackBackendApplication {

	public static void main(String[] args) {
		SpringApplication.from(FintrackBackendApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
