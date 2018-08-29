package org.springcloud.order.controller;

import org.springcloud.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	
	@RequestMapping(value="addOrderInfo")
	@ResponseBody
	public String addOrderInfo(String name,int amount){
		String msg = null;
		try {
			//1、向订单系统表插入数据
			orderService.save(name, amount);
			msg = name+"订单新增成功,此单数量："+amount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
}
