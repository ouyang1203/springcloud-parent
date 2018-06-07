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
	 * http://localhost:1010/getToken?clientCode=client&clientPwd=IVhRCE&redirect_uri=http://www.baidu.com
	 * 使用restclient能获取到对应的token,但是代码里使用POST请求获取时会报错
	 * */
	@GetMapping(value="getToken")
	public String getOauth2TokenByClientAndCode(@RequestParam String clientCode,@RequestParam String clientPwd,@RequestParam String redirect_uri){
		Map<String, String> param = Maps.newHashMap();
		param.put("clientCode", clientCode);
		param.put("clientPwd", clientPwd);
		param.put("redirect_uri", redirect_uri);
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		HttpEntity<String> formEntity = new HttpEntity<String>( headers);
		String postUrl = "http://localhost:1010/oauth/token?grant_type=authorization_code&code=${clientPwd}&client_id=${clientCode}&client_secret=${clientCode}&redirect_uri=${redirect_uri}";
		Object[] out = {postUrl,clientCode,clientPwd,redirect_uri};
		log_.info("postUrl is :{},clientCode is {},clientPwd is {},redirect_uri is {}",out);
		return restTemplate.postForEntity(postUrl, formEntity, String.class,param).getBody();
	}
}
