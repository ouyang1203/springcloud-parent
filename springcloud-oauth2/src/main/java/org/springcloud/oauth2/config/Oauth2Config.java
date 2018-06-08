package org.springcloud.oauth2.config;

import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
public class Oauth2Config extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
    private DataSource dataSource;
	@Bean
	// 声明TokenStore实现
	public TokenStore tokenStore(){
		return new JdbcTokenStore(dataSource);
	} 
	@Bean // 声明 ClientDetails实现
	public ClientDetailsService clientDetailsService(){
		return new JdbcClientDetailsService(dataSource);
	} 
	@Override // 配置框架应用上述实现
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
        endpoints.tokenStore(tokenStore());

        // 配置TokenServices参数
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(endpoints.getTokenStore());
        tokenServices.setSupportRefreshToken(false);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        tokenServices.setAccessTokenValiditySeconds( (int) TimeUnit.DAYS.toSeconds(30)); // 30天
        endpoints.tokenServices(tokenServices);
    }
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource);
	}
	/**
	 * client存储在内存中
	 * */
//	@Override
//	 public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.inMemory()//使用in-memory存储
//			.withClient("client") // client_id
//			.secret("secret") // client_secret
//			.authorizedGrantTypes("authorization_code") // 该client允许的授权类型
//			.scopes("app") // 允许的授权范围
//			.and()
//			.withClient("client_b")
//			.secret("secret") // client_secret
//			.authorizedGrantTypes("authorization_code") // 该client允许的授权类型
//			.scopes("app") ;
//	 }
	/**
	 * 不重写该方法时获取token会提示401 Full authentication is required to access this resource
	 * */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {  
		//enable client to get the authenticated when using the /oauth/token to get a access token  
		//there is a 401 authentication is required if it doesn't allow form authentication for clients when access /oauth/token
		oauthServer.allowFormAuthenticationForClients();  
	}
}
