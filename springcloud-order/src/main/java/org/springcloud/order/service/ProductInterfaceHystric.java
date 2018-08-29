package org.springcloud.order.service;

import org.springframework.stereotype.Component;

@Component
public class ProductInterfaceHystric implements ProductInterface{

	@Override
	public int update(String name,int amount) {
		return 0;
	}

}
