package org.springcloud.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
/**
 * @EnableDiscoveryClient注解来添加发现服务能力<br>
 * @LoadBalanced注解开启均衡负载能力。
 * @EnableHystrix注解开启Hystrix断路器
 * @EnableHystrixDashboard注解开启hystrixDashboard
 * */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
@ComponentScan
public class RibbonApplication {
	@Bean
	@LoadBalanced
	RestTemplate restTemplate (){
		return new RestTemplate();
	} 
	public static void main(String[] args) {
		SpringApplication.run(RibbonApplication.class, args);
	}
}
