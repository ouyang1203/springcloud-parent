package org.springcloud.feign.controller;

import org.springcloud.feign.service.FeignIntegerFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {
	
	@Autowired
	FeignIntegerFace face;
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(@RequestParam Integer a,@RequestParam Integer b){
		return face.add(a,b);
	}
}
