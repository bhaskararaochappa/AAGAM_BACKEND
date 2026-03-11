package com.aagam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AagamApplication {

	public static void main(String[] args) {
		System.out.println("main class bhaskar");
		SpringApplication.run(AagamApplication.class, args);
	}

}
