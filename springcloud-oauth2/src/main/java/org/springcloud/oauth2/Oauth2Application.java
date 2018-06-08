package org.springcloud.oauth2;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 *@EnableAuthorizationServer设置为Oauth2认证服务器
 *@授权访问页面IP：port/oauth/authorize?client_id=client&response_type=code&redirect_uri=http://www.baidu.com
 *@token获取页面http://localhost:1010/getToken?client_id=client&code=QXYvHN&client_secret=secret&redirect_uri=http://www.baidu.com
 *@继承 WebMvcConfigurerAdapter实现自定义登录页面和授权确认页面
 * */
@SpringBootApplication
@EnableAuthorizationServer
public class Oauth2Application{
	
	@Bean
	RestTemplate restTemplate (){
		return new RestTemplate();
	} 
	
	/**
	 * 配置spring datasource
	 * 使用C3P0连接池
	 * 在application.properties中设置datasource的前缀必须由是spring.datasource开始
	 * */
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource(){
		return  new ComboPooledDataSource();
	}
	/**
	 * 读取mybatis配置文件并获取sessionFactory
	 * */
	@Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }
	/**
	 * 添加事务管理
	 * */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
	
	public static void main(String[] args) {
		SpringApplication.run(Oauth2Application.class, args);
	}
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		//自定义登录跳转页面
//		registry.addViewController("/login").setViewName("login");
//		//自定义授权确认页面
//		registry.addViewController("/oauth/confirm_access").setViewName("authorize");
//
//	}
}
