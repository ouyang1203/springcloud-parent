package org.springcloud.product.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.codingapi.tx.netty.service.TxManagerHttpRequestService;
import com.lorne.core.framework.utils.http.HttpUtils;
@Service
public class TxManagerHttpRequestServiceImpl implements TxManagerHttpRequestService{

	private Logger log_ = LoggerFactory.getLogger(TxManagerHttpRequestServiceImpl.class);
	
	@Override
	public String httpGet(String url) {
		log_.info("httpGet start");
		String res = HttpUtils.get(url);
		log_.info("httpGet end,res is {}",res);
		return res;
	}

	@Override
	public String httpPost(String url, String params) {
		log_.info("httpPost start");
		String res = HttpUtils.post(url, params);
		log_.info("httpPost end,res is {}",res);
		return res;
	}

}
