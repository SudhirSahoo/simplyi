package com.skumar.kms.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class KmsConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(KmsConfigApplication.class, args);
	}

}