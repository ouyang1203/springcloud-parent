package org.springcloud.product.dao;

public interface ProductDao {
	public int update(String name,int amount);
	
	public int insert(String name,int amount);
}
