package org.springcloud.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.client.RestTemplate;
/**
 *@EnableAuthorizationServer设置为Oauth2认证服务器
 *@授权访问页面IP：port/oauth/authorize?client_id=client&response_type=code&redirect_uri=http://www.baidu.com
 * */
@SpringBootApplication
@EnableAuthorizationServer
public class Oauth2Application {
	
	@Bean
	RestTemplate restTemplate (){
		return new RestTemplate();
	} 
	
	public static void main(String[] args) {
		SpringApplication.run(Oauth2Application.class, args);
	}
}
