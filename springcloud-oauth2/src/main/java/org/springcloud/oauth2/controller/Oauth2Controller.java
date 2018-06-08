package org.springcloud.oauth2.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Maps;


@RestController
public class Oauth2Controller {
	
	private Logger log_ = LoggerFactory.getLogger(Oauth2Controller.class);
	
	@Autowired
	RestTemplate restTemplate;
	/**
	 * http://localhost:1010/getToken?client_id=client&code=IVhRCE&client_secret=secret&redirect_uri=http://www.baidu.com
	 * restclient 测试url如下：http://localhost:1010/oauth/token?grant_type=authorization_code&code=Hx4zJX&client_id=client&client_secret=secret&redirect_uri=http://www.baidu.com
	 * */
	@GetMapping(value="getToken")
	public String getOauth2TokenByClientAndCode(@RequestParam String client_id,@RequestParam String code,@RequestParam String client_secret,@RequestParam String redirect_uri){
		Map<String, String> param = Maps.newHashMap();
		param.put("client_id", client_id);
		param.put("code", code);
		param.put("client_secret", client_secret);
		param.put("redirect_uri", redirect_uri);
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		HttpEntity<String> formEntity = new HttpEntity<String>( headers);
		String postUrl = "http://localhost:1010/oauth/token?grant_type=authorization_code&code={code}&client_id={client_id}&client_secret={client_secret}&redirect_uri={redirect_uri}";
		Object[] out = {postUrl,client_id,code,client_secret,redirect_uri};
		log_.info("postUrl is :{},client_id is {},code is {},client_secret is {},redirect_uri is {}",out);
		return restTemplate.postForEntity(postUrl, formEntity, String.class,param).getBody();
	}
}
