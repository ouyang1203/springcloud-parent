package org.springcloud.order.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

import com.codingapi.tx.config.service.TxManagerTxUrlService;
/**
 * txManager负载均衡通过服务发现方式进行配置的个性化类
 * */
@Service
public class TxManagerTxUrlServiceImpl implements TxManagerTxUrlService{
	
	private Logger log_ = LoggerFactory.getLogger(TxManagerTxUrlServiceImpl.class);
	
	@Autowired
	private LoadBalancerClient loadBalancerClient; 
	@Value("${tm.manager.serviceId}")
	private String serviceId;
	
	@Override
	public String getTxUrl() {
		ServiceInstance serviceInstance = loadBalancerClient.choose(serviceId);
		String url = "http://"+ serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/tx/manager/";
		log_.info("current serviceId is {} current url for TxManagerTxUrlService is {}",new Object[]{serviceId,url});
		return url;
	}
	
}
