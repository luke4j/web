package com.luke.web.tool.aop;

import com.luke.web.tool.LK;
import com.luke.web.tool.web.ActResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    private static final Logger log = LoggerFactory.getLogger(LogAop.class) ;
//com.luke.web.login.controller.impl
    @Pointcut("execution(* com.luke.web.*.controller.impl.*.*(..))")
    private void point() {
    }

    @Around("point()")
    public Object around (ProceedingJoinPoint jp) throws Throwable{
        HttpServletRequest request = null;
        HttpServletResponse response = null ;
        ActResult<?> actResult = null ;
        BindingResult bindingResult = null ;
        for (Object obj:jp.getArgs() ) {
            if(obj instanceof HttpServletRequest)
                request = (HttpServletRequest)obj ;
            if(obj instanceof HttpServletResponse )
                response = (HttpServletResponse)obj ;
            if(obj instanceof ActResult)
                actResult = (ActResult)obj ;
        }
        Date start = new Date() ;
        if(log!=null && log.isDebugEnabled()){
            log.debug("===start is "+ LK.DateToStr(start,"yyyy-MM-dd HH:mm:ss SSS"));
            if(request!=null){
                log.debug("===uri is======="+request.getRequestURI());
                log.debug("===params is ==="+LK.ObjToJsonStr(request.getParameterMap()));
            }
        }
        Object obj = jp.proceed(jp.getArgs()) ;
        Date end = new Date() ;
        if(log!=null&& log.isDebugEnabled()){
            log.debug("==========>:end time "+ LK.DateToStr(end,"yyyy-MM-dd HH:mm:ss SSS"));
            log.debug("==========>:time is "+(end.getTime()-start.getTime())+"\n\r");
        }
        return obj ;
    }

}
