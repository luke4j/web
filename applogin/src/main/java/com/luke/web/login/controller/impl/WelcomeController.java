package com.luke.web.login.controller.impl;

import com.luke.web.login.controller.IWelcomeController;
import com.luke.web.login.service.ILoginService;
import com.luke.web.tool.LK;
import com.luke.web.tool.exception.AppException;
import com.luke.web.tool.web.ActResult;
import com.luke.web.vo.login.VOInLogin;
import com.luke.web.vo.login.VOoutInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;

@Controller
public class WelcomeController implements IWelcomeController {
    private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class) ;

    @Value("${login.sysname}")
    String sysname ;

    @Resource
    ILoginService loginService ;

    @Override
    public String welcome(HttpServletRequest request,VOInLogin vo, Model model) throws AppException {
        model.addAttribute("sysname",sysname) ;
        model.addAttribute("systime",new Date()) ;
        if(LK.StrIsEmpty(vo.getLoginTuken())){
            return "login";
        }else{
            VOoutInfo tokenVal = this.loginService.getTokenIsValid(vo.getLoginTuken()) ;
            if(tokenVal==null){
                return "login";
            }else{
                return "/work" ;
            }

        }
    }

    @Override
    public ActResult<VOoutInfo> login(HttpServletRequest request, HttpServletResponse response,
                                      ActResult<VOoutInfo> actResult,
                                      @Valid VOInLogin vo, BindingResult result) throws AppException {
        actResult.setDoing("登录");
        VOoutInfo staff = loginService.findLoginUser(vo,actResult) ;
        actResult.setData(staff);
        return actResult;
    }
}
