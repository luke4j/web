package com.luke.web.login.service;

import com.luke.web.tool.exception.AppException;
import com.luke.web.tool.web.ActResult;
import com.luke.web.vo.login.VOInLogin;
import com.luke.web.vo.login.VOoutInfo;

public interface ILoginService {
    /**
     * 登录方法
     * @param vo
     * @return
     * @throws AppException
     */
    VOoutInfo findLoginUser(VOInLogin vo, ActResult<VOoutInfo> art) throws AppException;
}
