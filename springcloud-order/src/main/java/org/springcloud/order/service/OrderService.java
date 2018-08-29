package org.springcloud.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springcloud.order.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingapi.tx.annotation.ITxTransaction;
import com.codingapi.tx.annotation.TxTransaction;
@Service
public class OrderService implements ITxTransaction{
	private Logger log_ = LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private ProductInterface productInterface;
	/**
	 * 插入订单系统数据
	 * */
	@TxTransaction(isStart=true)
	@Transactional
	public int save(String name,int amount){
		//FEIGN远程调用库存模块
		int b = productInterface.update(name, amount);
		log_.info("库存模块更新完成,本次更新的产品为:{},更新后数量为:{}",new Object[]{name,amount});
		//当前服务的事物回滚成功
		int a = orderDao.save(name, amount);
		log_.info("订单模块插入完成,本次新增订单产品为:{},产品数量为:{}",new Object[]{name,amount});
		int c = 100/0;
		return a+b;
	}
	
	
}
