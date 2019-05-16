package com.luke.web.login.controller.impl;

import com.luke.web.login.controller.IWelcomeController;
import com.luke.web.tool.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Date;

@Controller
public class WelcomeController implements IWelcomeController {
    private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class) ;

    @Value("${login.sysname}")
    String sysname ;

    @Override
    public String gotoLogin1(Model model) throws AppException {
        model.addAttribute("sysname",sysname) ;
        model.addAttribute("systime",new Date()) ;
        return "login";
    }
}
