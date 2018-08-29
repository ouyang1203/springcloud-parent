package org.springcloud.order.dao.impl;

import org.springcloud.order.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class OrderDaoImpl implements OrderDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int save(String name,int amount) {
		String sql = "insert into order_info (product_name,product_amount) values(?,?)";
		return jdbcTemplate.update(sql,new Object[]{name,amount});
	}
	
}
