package org.springcloud.feign.service;

import org.springframework.stereotype.Component;

@Component
public class FeignIntegerFaceHystric implements FeignIntegerFace{
	@Override
	public String add(Integer a, Integer b) {
		return "sorry the server is not start";
	}

}
