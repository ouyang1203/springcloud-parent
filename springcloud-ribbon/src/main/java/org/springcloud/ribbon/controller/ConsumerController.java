package org.springcloud.ribbon.controller;

import org.springcloud.ribbon.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
	
	@Autowired
	RibbonService ribbonService;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		return ribbonService.add();
	}
}
