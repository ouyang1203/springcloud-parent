package org.springcloud.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class RibbonService {
	@Autowired
	RestTemplate restTemplate;
	/**
	 * @HystrixCommand(fallbackMethod = "addError")指定服务器未正常运行时处理方法
	 * */
	@HystrixCommand(fallbackMethod = "addError")
	public String add() {
		return restTemplate.getForEntity("http://SERVICE/add?a=10&b=20", String.class).getBody();
	}
	
	public String addError(){
		return "sorry the server is not start";
	}
}
