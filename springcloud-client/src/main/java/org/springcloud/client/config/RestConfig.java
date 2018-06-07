package org.springcloud.client.config;

import java.nio.charset.Charset;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.sun.jersey.core.util.Base64;

@Configuration
@ConfigurationProperties(ignoreUnknownFields = false,prefix = "springcloud.security.auth")
public class RestConfig {
	
	private String accountAuth ;
	
	 public String getAccountAuth() {
		return accountAuth;
	}
	public void setAccountAuth(String accountAuth) {
		this.accountAuth = accountAuth;
	}
	@Bean
	 public RestTemplate getRestTemplate(){
		 return new RestTemplate() ;  
	 } 
	 //定义一个Bean修改头信息进行客户端认证  
	 @Bean
	 public HttpHeaders getHeader() { 
		 HttpHeaders headers = new HttpHeaders();  
		 String auth = accountAuth;//认证的原始信息  
		 byte[] encodeAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));//将原始认证信息进行Base64加密  
		 String authHeader = "Basic "+new String(encodeAuth);//加密后的认证信息要与Basic有个空格 
		 headers.set("Authorization", authHeader);  
		 return headers;
	 }
}
