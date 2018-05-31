package org.springcloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
@EnableZuulProxy 开启zuul的路由功能
@EnableEurekaClient 向服务注册中心注册
@SpringBootApplication
测试地址如下,此应用端口不能配置为6666
http://localhost:7777/api-a/add?token=aaaaa   ->此地址跳转到SERVICE-RIBBON中请求
http://localhost:7777/api-b/add?token=aaaaa&a=12&b=23   ->此地址跳转到SERVICE-FEIGN中请求
将请求进行转发
 * */
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class ZuulApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}
}
