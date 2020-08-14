package com.skumar.kms.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class KmsDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KmsDiscoveryServiceApplication.class, args);
	}

}
