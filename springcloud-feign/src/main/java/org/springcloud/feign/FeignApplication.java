package org.springcloud.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @EnableDiscoveryClient开启服务注册
 * @EnableFeignClients注解开启Feign的功能
 * @EnableHystrix注解开启Hystrix断路器
 * @EnableHystrixDashboard注解开启hystrixDashboard
 * */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
public class FeignApplication {
	public static void main(String[] args) {
		SpringApplication.run(FeignApplication.class, args);
	}
}
