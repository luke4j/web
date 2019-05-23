package com.luke.web.tool.web;

import com.luke.web.tool.LK;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@ControllerAdvice
public class WebExceptionHandler {
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ActResult<Map<String,Object>> handlerException(HttpServletRequest request, HttpServletResponse response, Throwable e){
        e.printStackTrace();
        ActResult<Map<String,Object>> actionResult = new ActResult<Map<String,Object>>() ;
        actionResult.setRequest(request);
        String msg = e.getMessage() ;
        if(LK.StrIsEmpty(msg))
            msg = e.getClass().toString() ;
        actionResult.setErrMsg(msg);
        actionResult.setError(e);
        actionResult.setDoing("请求异常");
        actionResult.setStatus("error-1001");
        return actionResult ;
    }
}
