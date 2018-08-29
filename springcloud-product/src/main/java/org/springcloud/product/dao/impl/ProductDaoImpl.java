package org.springcloud.product.dao.impl;

import org.springcloud.product.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class ProductDaoImpl implements ProductDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int update(String name,int amount) {
		String sql = "update product set product_amount=? where product_name=?";
		return jdbcTemplate.update(sql, new Object[]{amount,name});
	}

	@Override
	public int insert(String name, int amount) {
		String sql = "insert into product (product_name,product_amount)values(?,?)";
		return jdbcTemplate.update(sql, new Object[]{name,amount});
	}

}
