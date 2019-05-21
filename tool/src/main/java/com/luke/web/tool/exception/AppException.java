package com.luke.web.tool.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(AppException.class) ;

    private AppException (String msg){
        super(msg);
        logger.error("AppException msg is "+msg);
    }

    public static AppException create(String msg){
        AppException ae = new AppException("error-"+msg) ;
        return ae ;
    }

    public static AppException create(String modelName,String msg){
        AppException ae = new AppException("error-"+modelName+"-"+msg) ;
        return ae ;
    }
}
