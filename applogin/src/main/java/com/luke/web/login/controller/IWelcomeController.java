package com.luke.web.login.controller;

import com.luke.web.tool.exception.AppException;
import com.luke.web.tool.web.ActResult;
import com.luke.web.tool.vo.VOIn;
import com.luke.web.tool.vo.VOOut;
import com.luke.web.vo.login.VOInLogin;
import com.luke.web.vo.login.VOInUpdatePwd;
import com.luke.web.vo.login.VOOutMenu;
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

    /***
     * 欢迎页面
     * @param request
     * @param vo
     * @param model
     * @return
     * @throws AppException
     */
    @RequestMapping(path = "/",method = RequestMethod.GET)
    String welcome(HttpServletRequest request,VOInLogin vo,Model model) throws AppException;

    /**
     * 用户名登录
     * @param request
     * @param response
     * @param actResult
     * @param vo
     * @param result
     * @return
     * @throws AppException
     */
    @RequestMapping(path = "login.act",method = RequestMethod.POST)
    @ResponseBody
    ActResult<VOoutInfo> login(HttpServletRequest request, HttpServletResponse response,
                               ActResult<VOoutInfo> actResult,
                               @Valid VOInLogin vo , BindingResult result) throws AppException ;

    /**
     * loginToken 查询用户权限
     * @param request
     * @param response
     * @param actResult
     * @param vo
     * @param result
     * @return
     * @throws AppException
     */
    @RequestMapping(path = "getRole.act",method = RequestMethod.POST)
    @ResponseBody
    ActResult<VOOutMenu> getRole(HttpServletRequest request, HttpServletResponse response,
                                 ActResult<VOOutMenu> actResult,
                                 @Valid VOIn vo , BindingResult result) throws AppException ;
    /**
     * token 登录
     * @param request
     * @param response
     * @param actResult
     * @param vo
     * @param result
     * @return
     * @throws AppException
     */
    @RequestMapping(path = "getUserInfo.act",method = RequestMethod.POST)
    @ResponseBody
    ActResult<VOoutInfo> getUserInfo(HttpServletRequest request, HttpServletResponse response,
                                     ActResult<VOoutInfo> actResult,
                                     @Valid VOInLogin vo,BindingResult result) throws AppException ;

    /**
     * 登出访求
     * @param request
     * @param response
     * @param actResult
     * @param vo
     * @param result
     * @return
     * @throws AppException
     */
    @RequestMapping(path = "logout.act",method = RequestMethod.POST)
    @ResponseBody
    ActResult<VOOut> logout(HttpServletRequest request, HttpServletResponse response,
                         ActResult<VOOut> actResult,
                         @Valid VOInLogin vo, BindingResult result)throws AppException ;

    /**
     * 个人修改密码方法
     * @param request
     * @param response
     * @param actResult
     * @param vo
     * @param result
     * @return
     * @throws AppException
     */
    @RequestMapping(path = "updatePassword.act",method = RequestMethod.POST)
    @ResponseBody
    ActResult<VOOut> updatePassword (HttpServletRequest request, HttpServletResponse response,
                                     ActResult<VOOut> actResult,
                                     @Valid VOInUpdatePwd vo, BindingResult result)throws AppException ;

    @RequestMapping(path = "getAppRootUrl.act",method = RequestMethod.POST)
    @ResponseBody
    ActResult<VOOut> getAppRootUrl( HttpServletRequest request, HttpServletResponse response,ActResult<VOOut> actResult) throws AppException ;


}
