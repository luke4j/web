package com.luke.web.login.controller.impl;

import com.luke.web.login.controller.IWelcomeController;
import com.luke.web.tool.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class WelcomeController implements IWelcomeController {
    private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class) ;
    @Override
    public String gotoLogin1() throws AppException {
        logger.info("===========================gotoLogin1");
        return "login";
    }
}
