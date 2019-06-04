package com.luke.web.login.controller.impl;

import com.luke.web.login.controller.IWelcomeController;
import com.luke.web.login.service.ILoginService;
import com.luke.web.model.Lgn_Role;
import com.luke.web.tool.LK;
import com.luke.web.tool.LKMap;
import com.luke.web.tool.exception.AppException;
import com.luke.web.tool.web.ActResult;
import com.luke.web.vo.VOIn;
import com.luke.web.vo.VOOut;
import com.luke.web.vo.login.VOInLogin;
import com.luke.web.vo.login.VOInUpdatePwd;
import com.luke.web.vo.login.VOOutMenu;
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
        String localhost = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
        model.addAttribute("localhost",localhost) ;
        if(LK.StrIsEmpty(vo.getLoginTuken())){
            return "login";
        }else{
            VOoutInfo tokenVal = this.loginService.getTokenIsValid(vo.getLoginTuken()) ;
            if(tokenVal==null){
                return "login";
            }else{
                return "work" ;
            }

        }
    }

    @Override
    public ActResult<VOoutInfo> login(HttpServletRequest request, HttpServletResponse response,
                                      ActResult<VOoutInfo> actResult,
                                      @Valid VOInLogin vo, BindingResult result) throws AppException {
        actResult.setDoing("用户名登录");
        VOoutInfo staff = loginService.findLoginUser(vo,actResult) ;
        actResult.setData(staff);
        return actResult;
    }

    @Override
    public ActResult<VOOutMenu> getRole(HttpServletRequest request, HttpServletResponse response,
                                        ActResult<VOOutMenu> actResult,
                                        @Valid VOIn vo, BindingResult result) throws AppException {

        actResult.setDoing("以loginTuken查询权限");
        VOOutMenu role = loginService.getRole(vo,actResult) ;
        actResult.setData(role);
        return actResult;
    }

    @Override
    public ActResult<VOoutInfo> getUserInfo(HttpServletRequest request, HttpServletResponse response,
                                            ActResult<VOoutInfo> actResult,
                                            @Valid VOInLogin vo, BindingResult result) throws AppException {
        actResult.setDoing("token登录");
        VOoutInfo staff = loginService.getTokenIsValid(vo.getLoginTuken()) ;
        actResult.setData(staff);
        return actResult;
    }

    @Override
    public ActResult<VOOut> logout(HttpServletRequest request, HttpServletResponse response,
                                   ActResult<VOOut> actResult,
                         @Valid VOInLogin vo, BindingResult result)throws AppException{
        actResult.setDoing("token登出");
        loginService.delToken(vo.getLoginTuken()) ;
        return actResult ;
    }

    @Override
    public ActResult<VOOut> updatePassword(HttpServletRequest request, HttpServletResponse response,
                                           ActResult<VOOut> actResult,
                                           @Valid VOInUpdatePwd vo, BindingResult result) throws AppException {

        actResult.setDoing("修改个人密码");
        loginService.updatePassword(vo,actResult) ;
        actResult.setMsg("密码修改成功");
        return actResult;
    }

    @Override
    public ActResult<VOOut> getAppRootUrl( HttpServletRequest request, HttpServletResponse response,
                                           ActResult<VOOut> actResult) throws AppException {
        actResult.setDoing("得到程序url路径");
        String contextPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
        VOOut out = new VOOut() ;
        out.setInfo(contextPath);
        actResult.setData(out);
        return actResult;
    }
}
