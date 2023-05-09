package com.amod.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GetNameServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetNameServiceApplication.class, args);
	}

}
