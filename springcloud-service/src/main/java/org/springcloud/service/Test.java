package org.springcloud.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Test {
	
	public static void main(String[] args) {
		SpringApplication.run(Test.class, args);
	}
	
}
