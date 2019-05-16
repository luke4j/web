package com.luke.web.login.controller;

import com.luke.web.tool.exception.AppException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface IWelcomeController {

    @RequestMapping(path = "/",method = RequestMethod.GET)
    String gotoLogin1(Model model) throws AppException;

    @RequestMapping(path = "work",method = RequestMethod.GET)
    String work(Model model) throws AppException;
}
