package com.luke.web.tool;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActResult<T> {

    @JsonIgnore
    HttpServletRequest request ;
    public ActResult(){}
    public void setRequest(HttpServletRequest request) {
        this.request = request;
        this.url = request.getRequestURI() ;
        this.params = request.getParameterMap() ;
    }

    public  ActResult(HttpServletRequest request ){
        this.url = request.getRequestURI() ;
        this.params = request.getParameterMap() ;
    }



    String url ;
    Map<String,String[]> params ;
    String fun ;


    /**请求是否成功*/
    String success = "true" ;
    /**状态码 正常:200，异常:err-模块-url */
    String status = "200";

    /**数据*/
    T data ;

    /**总数*/
    Long count = 0l;

    /**扩展信息*/
    Map<String,Object> extend = new HashMap<String,Object>(10) ;

    /**后台提示*/
    String msg = "";

    /**异常*/
    Throwable error ;

    /**异常信息*/
    String errMsg = "";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String[]> getParams() {
        return params;
    }

    public void setParams(Map<String, String[]> params) {
        this.params = params;
    }

    public String getFun() {
        return fun;
    }

    public void setFun(String fun) {
        this.fun = fun;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
