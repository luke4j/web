package com.luke.web.login.controller;

import com.luke.web.tool.exception.AppException;
import com.luke.web.tool.web.ActResult;
import com.luke.web.vo.login.VOInLogin;
import com.luke.web.vo.login.VOoutInfo;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * url=login
 */
@RequestMapping(path = "login")
public interface ILoginController {

    /**
     * 登录接口
     * url=login.act
     * @param request
     * @param response
     * @param vo
     * @param result
     * @return
     * @throws AppException
     */
    @RequestMapping(path = "login.act",method = RequestMethod.POST)
    ActResult<VOoutInfo> login(HttpServletRequest request, HttpServletResponse response,
                               ActResult<VOoutInfo> ar,
                               @Valid VOInLogin vo , BindingResult result) throws AppException ;



}
