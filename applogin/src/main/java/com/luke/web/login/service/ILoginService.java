package com.luke.web.login.service;

import com.luke.web.model.Lgn_Role;
import com.luke.web.tool.exception.AppException;
import com.luke.web.tool.web.ActResult;
import com.luke.web.vo.VOIn;
import com.luke.web.vo.VOOut;
import com.luke.web.vo.login.VOInLogin;
import com.luke.web.vo.login.VOInUpdatePwd;
import com.luke.web.vo.login.VOOutMenu;
import com.luke.web.vo.login.VOoutInfo;

public interface ILoginService {
    /**
     * 登录方法
     * @param vo
     * @return
     * @throws AppException
     */
    VOoutInfo findLoginUser(VOInLogin vo, ActResult<VOoutInfo> art) throws AppException;

    /**
     * 查询token，返回VOoutInfo
     * @param loginTuken
     * @return
     * @throws AppException
     */
    VOoutInfo getTokenIsValid(String loginTuken)throws AppException;

    /**
     * 登出访求
     * @param loginTuken
     * @throws AppException
     */
    void delToken(String loginTuken)throws AppException;

    /**
     * 修改个人密码
     * @param vo
     * @param actResult
     * @return
     */
    void updatePassword(VOInUpdatePwd vo, ActResult<VOOut> actResult)throws AppException;

    /**
     * 以loginToken查询权限
     * @param vo
     * @param actResult
     * @return
     * @throws AppException
     */
    VOOutMenu getRole(VOIn vo, ActResult<VOOutMenu> actResult)throws AppException;
}
