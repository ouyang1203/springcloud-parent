package org.spring.cloud.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class ClientApplication {
    @Value("${test}")
    String test;

    @RequestMapping("/")
    public String home() {
        return "content:" + test;
    }
    public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}
}
