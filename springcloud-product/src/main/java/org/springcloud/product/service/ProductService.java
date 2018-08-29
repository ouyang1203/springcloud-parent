package org.springcloud.product.service;

import org.springcloud.product.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingapi.tx.annotation.ITxTransaction;
@Service
public class ProductService implements ITxTransaction{
	@Autowired
	private ProductDao productDao;
	
	@Transactional
	public int update(String name,int amount) {
		return productDao.update(name,amount);
	}
}
