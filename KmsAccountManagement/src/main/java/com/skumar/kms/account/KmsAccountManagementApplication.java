package com.skumar.kms.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class KmsAccountManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(KmsAccountManagementApplication.class, args);
	}

}
