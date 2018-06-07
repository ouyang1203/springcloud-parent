package org.springcloud.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientController {
	 @Autowired
	 private RestTemplate restTemplate;
	 @Autowired
	 private HttpHeaders headers;
	 
	 @RequestMapping (name="sayHello" ,method=RequestMethod.GET)
	 public String sayHello(@RequestParam String name){
		 String str = restTemplate.exchange("http://localhost:1000/hello?name="+name, HttpMethod.GET,
				 new HttpEntity<Object>(headers), String.class).getBody();
		 return str;
	 }
}
