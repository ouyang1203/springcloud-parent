package org.springcloud.product.controller;

import org.springcloud.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	@RequestMapping(value="test")
	@ResponseBody
	public String test(){
		return "test";
	}
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="updateProduct")
	public String updateProduct(String name,int amount){
		String msg = "";
		try {
			int num = productService.update(name, amount);
			msg = num+"";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
}
