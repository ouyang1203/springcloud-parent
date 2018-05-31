package org.springcloud.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
	@Autowired
	private Registration registration;
	
	@RequestMapping(value = "/add" ,method = RequestMethod.GET)
	public Integer add(@RequestParam Integer a,@RequestParam Integer b) {
		String serviceId = registration.getServiceId();
		Integer r = a + b;
		System.out.println("serviceId===="+serviceId);
		return r;

	}
}
