package org.springcloud.order.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "SERVICE-PRODUCT",fallback=ProductInterfaceHystric.class)
public interface ProductInterface {
	@RequestMapping(value="updateProduct")
	public int update(@RequestParam(value="name")String name,@RequestParam(value="amount")int amount) ;
}
