package com.luke.web.login.controller.impl;

import com.luke.web.login.controller.ILoginController;
import com.luke.web.login.service.ILoginService;
import com.luke.web.model.U_Staff;
import com.luke.web.tool.exception.AppException;
import com.luke.web.tool.web.ActResult;
import com.luke.web.vo.login.VOInLogin;
import com.luke.web.vo.login.VOoutInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;

@RestController
public class LoginController implements ILoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class) ;

    @Resource
    ILoginService loginService ;

    @Override
    public ActResult<VOoutInfo> login(HttpServletRequest request, HttpServletResponse response,
                                      ActResult<VOoutInfo> art,
                                      @Valid VOInLogin vo, BindingResult result) throws AppException {
        art.setDoing("登录");
        VOoutInfo staff = loginService.findLoginUser(vo,art) ;
        art.setData(staff);
        art.getExtend().put("systime",new Date()) ;
        return art;
    }
}
