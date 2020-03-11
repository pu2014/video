package com.webframe.genderate.common.aspect;

import com.alibaba.fastjson.JSON;
import com.webframe.genderate.common.annotation.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

/**
 * 切面 打印请求、返回参数信息
 */
@Aspect // 定义一个切面
@Configuration
public class LogRecordAspect {
	private static final Logger logger = LoggerFactory.getLogger(LogRecordAspect.class);

    // 定义切点Pointcut
  //  @Pointcut("execution(* com.webframe.genderate.controller.*Controller.*(..))")
    @Pointcut("@annotation(com.webframe.genderate.common.annotation.Log)")
    public void excudeService() {}

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method signatureMethod = signature.getMethod();
        Log log = signatureMethod.getAnnotation(Log.class);
        String methodName = signatureMethod.getName(); //方法名称
        String type = request.getMethod(); //请求类型
        String uri = request.getRequestURI(); //请求URL
        
        String paraString = JSON.toJSONString(request.getParameterMap()); //请求参数
        logger.info("***************************************************");
        logger.info("请求开始, URI: {}, methodName: {}, description: {}, type: {}, params: {}", uri, methodName, log.value(), type, paraString);

        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed(); //返回对象
        logger.info("请求结束，controller的返回值是 " + JSON.toJSONString(result));
        return result;
    }
}
