package com.luke.web.login.controller;

import com.luke.web.tool.exception.AppException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(path = "login")
public interface ILoginController {

    @RequestMapping(path = "gotologin.act",method = RequestMethod.GET)
    String gotoLogin() throws AppException;

    @RequestMapping(path = "/",method = RequestMethod.GET)
    String gotoLogin1() throws AppException;




}
