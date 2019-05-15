package com.luke.web.tool.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(AppException.class) ;

    public AppException (String msg){
        super(msg);
        logger.error("AppException msg is "+msg);
    }

}
