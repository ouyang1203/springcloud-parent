package org.springcloud.zuul.filter;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
/**
filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下： 
	pre：路由之前
	routing：路由之时
	post： 路由之后
	error：发送错误调用
	filterOrder：过滤的顺序
	shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
	run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
 * */
@Component
public class ZuulFilterE extends ZuulFilter{
	private Logger log_ = Logger.getLogger(ZuulFilterE.class.getName());

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String uri = request.getRequestURL().toString();
        log_.info(String.format("%s >>> %s", request.getMethod(), uri));
        Object accessToken = request.getParameter("token");
        //只对api-a下面的请求进行鉴权
        if(accessToken == null&&uri.contains("/api-a/")) {
            log_.info("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            }catch (Exception e){}
            return null;
        }
        log_.info("ok");
		return null;
	}

	@Override
	public boolean shouldFilter() {
		 return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
