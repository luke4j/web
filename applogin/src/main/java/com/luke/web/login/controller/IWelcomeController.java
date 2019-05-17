package com.luke.web.login.controller;

import com.luke.web.tool.exception.AppException;
import com.luke.web.tool.web.ActResult;
import com.luke.web.vo.login.VOInLogin;
import com.luke.web.vo.login.VOoutInfo;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

public interface IWelcomeController {

    @RequestMapping(path = "/",method = RequestMethod.GET)
    String welcome(HttpServletRequest request,VOInLogin vo,Model model) throws AppException;

    @RequestMapping(path = "login.act",method = RequestMethod.POST)
    @ResponseBody
    ActResult<VOoutInfo> login(HttpServletRequest request, HttpServletResponse response,
                               ActResult<VOoutInfo> actResult,
                               @Valid VOInLogin vo , BindingResult result) throws AppException ;

}
