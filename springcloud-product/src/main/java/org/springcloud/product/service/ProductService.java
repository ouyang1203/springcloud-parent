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
		int a = productDao.update(name,amount);
		//测试客户端出现异常回滚事物的代码需要打开下面的int c = 100/0;
//		int c = 100/0;
		return a;
	}
}
