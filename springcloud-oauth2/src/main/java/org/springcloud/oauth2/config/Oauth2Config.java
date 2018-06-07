package org.springcloud.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
public class Oauth2Config extends AuthorizationServerConfigurerAdapter {
	
	@Override
	 public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()//使用in-memory存储
			.withClient("client") // client_id
			.secret("secret") // client_secret
			.authorizedGrantTypes("authorization_code") // 该client允许的授权类型
			.scopes("app"); // 允许的授权范围
	 }
	/**
	 * 不重写该方法时获取token会提示401
	 * */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {  
		//enable client to get the authenticated when using the /oauth/token to get a access token  
		//there is a 401 authentication is required if it doesn't allow form authentication for clients when access /oauth/token
		oauthServer.allowFormAuthenticationForClients();  
	}
}
