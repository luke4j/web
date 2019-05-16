package com.luke.web.tool.aop;

import com.luke.web.tool.exception.AppException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

@Component
@Aspect
public class ErrorAop {

    private static final Logger log = LoggerFactory.getLogger(ErrorAop.class) ;
    //com.luke.web.login.controller.impl
    @Pointcut("execution(* com.luke.web.*.controller.impl.*.*(..))")
    private void point() {
    }

    @Around("point()")
    public Object around (ProceedingJoinPoint jp) throws Throwable{
        BindingResult bindingResult = null ;
        for (Object obj:jp.getArgs()) {
            if(obj instanceof BindingResult){
                bindingResult = (BindingResult)obj ;
                break ;
            }
        }
        if(bindingResult!=null&&bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors() ;
            StringBuffer sb = new StringBuffer(errors.size()) ;
            for(ObjectError oe :errors){
                sb.append(oe.getDefaultMessage()).append(";") ;
            }
            log.error("!!!!!!!!!!"+sb.toString());
            throw AppException.create(sb.toString()) ;
        }
        try{
            Object obj = jp.proceed(jp.getArgs()) ;
            return obj ;
        }catch (Throwable e){
            log.error("!!!!!!!!!!"+e.getMessage());
            throw AppException.create(e.getMessage()) ;
        }
    }
}
