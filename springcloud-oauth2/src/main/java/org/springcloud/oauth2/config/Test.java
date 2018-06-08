package org.springcloud.oauth2.config;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.core.util.Base64;
/**
 * 获取base64加密
 * */
public class Test {
	private static Logger log_ = LoggerFactory.getLogger(Test.class);
	
	public static void main(String[] args) {
		String auth = "ouyang:ouyang";
		byte[] encodeAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));//将原始认证信息进行Base64加密  
		String res = new String(encodeAuth);
		log_.info("base64 encoder str is {}",res);
	}
}
