package org.springcloud.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	
	@RequestMapping(value ="/hello" ,method=RequestMethod.GET)
	public String sayhello(@RequestParam String name){
		return "Security hello:"+name;
	}
}
