package com.luke.web.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "login")
public interface ILoginController {

    @RequestMapping(path = "gotologin.act",method = RequestMethod.GET)
    String gotoLogin() ;




}
