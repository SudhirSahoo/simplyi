package com.skumar.kms.subjects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class KmsSubjectsApplication {

	public static void main(String[] args) {
		SpringApplication.run(KmsSubjectsApplication.class, args);
	}

}
