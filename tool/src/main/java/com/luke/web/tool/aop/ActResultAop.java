package com.luke.web.tool.aop;


import com.luke.web.tool.web.ActResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class ActResultAop {

    private static final Logger log = LoggerFactory.getLogger(ActResultAop.class) ;
    //com.luke.web.login.controller.impl
    @Pointcut("execution(* com.luke.web.*.controller.impl.*.*(..))")
    private void point() {
    }

    @Around("point()")
    public Object around (ProceedingJoinPoint jp) throws Throwable{
        ActResult<?> art = null ;
        HttpServletRequest request = null;
        for (Object obj:jp.getArgs()) {
            if(obj instanceof ActResult){
                art = (ActResult)obj ;
            }
            if(obj instanceof HttpServletRequest){
                request = (HttpServletRequest)obj ;
            }
        }
        if(art!=null&&request!=null){
            art.setRequest(request);
        }
        Object obj = jp.proceed(jp.getArgs()) ;
        return obj ;
    }
}
