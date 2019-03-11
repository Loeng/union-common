package com.union.aimei.aop.logs;

import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

@Aspect
@Order(1)
@Component
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    WebLogService webLogService;

    @Pointcut("execution(public * com.union.aimei.*.*.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

//        logger.info("URL : " + request.getRequestURL().toString());
//        logger.info("HTTP_METHOD : " + request.getMethod());
//        logger.info("IP : " + request.getRemoteAddr());
//        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        // 记录下请求内容
//        logger.info("requestURL : "+ request.getRequestURL().toString());
//        logger.info("requestURI : "+ request.getRequestURI());
//        logger.info("queryString : "+ request.getQueryString());
//        logger.info("remoteAddr : "+ request.getRemoteAddr());
//        logger.info("remoteHost : "+ request.getRemoteHost());
//        logger.info("remotePort : "+ request.getRemotePort());
//        logger.info("localAddr : "+ request.getLocalAddr());
//        logger.info("localName : "+ request.getLocalName());
//        logger.info("method : "+ request.getMethod());
//        logger.info("headers : "+ getHeadersInfo(request));
//        logger.info("parameters : "+ request.getParameterMap());
//        logger.info("classMethod : "+ joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        logger.info("args : "+ Arrays.toString(joinPoint.getArgs()));
        WebLog log = new WebLog();
        log.setRequestURL(request.getRequestURL().toString());
        log.setRequestURI(request.getRequestURI());
        log.setQueryString(request.getQueryString());
        log.setRemoteAddr(request.getRemoteAddr());
        log.setRemoteHost(request.getRemoteHost());
        log.setRemotePort(request.getRemotePort() + "");
        log.setLocalAddr(request.getLocalAddr());
        log.setLocalName(request.getLocalName());
        log.setMethod(request.getMethod());
        log.setParameters(request.getParameterMap().toString());
        log.setHeaders(getHeadersInfo(request).toString());
        log.setClassMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.setArgs(Arrays.toString(joinPoint.getArgs()));

//        log.setOperateId(getHeadersInfo(request).get("operateid"));
//        logger.info("operateid--------------------->"+request.getHeader("operateid"));
//        logger.info("operateId--------------------->"+request.getHeader("operateId"));
//        logger.info("operateusername--------------------->"+request.getHeader("operateusername"));
//        logger.info("operateUserName--------------------->"+request.getHeader("operateUserName"));
        log.setOperateId(request.getHeader("operateId"));
//        log.setOperateUserName(getHeadersInfo(request).get("operateusername"));
        log.setOperateUserName(request.getHeader("operateUserName"));
        log.setCreateTime(new Date());


        //
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();

        Class clazz = targetMethod.getClass();
        if (targetMethod.isAnnotationPresent(ApiOperation.class)) {
            //获取方法上注解中表明的权限
            ApiOperation apiOperation = targetMethod.getAnnotation(ApiOperation.class);
            log.setOperateName(apiOperation.value());
        }

        if (targetMethod.getName().startsWith("find") || targetMethod.getName().startsWith("select") || targetMethod.getName().equals("insertLogs")|| targetMethod.getName().startsWith("query")) {

        } else {
            webLogService.save(log);
        }

    }

//    @AfterReturning(returning = "ret : "+ pointcut = "webLog()")
//    public void doAfterReturning(Object ret) throws Throwable {
//        // 处理完请求，返回内容
//        logger.info("RESPONSE : " + ret.toString());
//    }

    private Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
//            logger.info(key+value);
        }
        return map;
    }
}

