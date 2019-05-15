package com.luke.web.login.controller;

import com.luke.web.tool.exception.AppException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface IWelcomeController {

    @RequestMapping(path = "/",method = RequestMethod.GET)
    String gotoLogin1() throws AppException;
}
