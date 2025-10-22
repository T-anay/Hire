package com.talhaanay.is_ilan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "com.talhaanay")
@SpringBootApplication
public class IsIlanApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsIlanApplication.class, args);
	}

}
