package org.springcloud.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * fallback=FeignIntegerFaceHystric.class在Feign中使用熔断机制指定当调用的服务出错时请求的处理方法,其中FeignIntegerFaceHystric必须要实现这个接口
 * 通过@FeignClient中的value指定服务中心注册的服务地址来请求
 * */
@FeignClient(value = "SERVICE",fallback=FeignIntegerFaceHystric.class)
public interface FeignIntegerFace {
	 @RequestMapping(value = "/add",method = RequestMethod.GET)
	 public String add(@RequestParam(value = "a") Integer a,@RequestParam(value = "b") Integer b);
}
