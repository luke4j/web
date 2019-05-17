package com.luke.web.login.dao;

import com.luke.web.model.U_Staff;
import com.luke.web.tool.exception.AppException;
import com.luke.web.vo.login.VOoutInfo;

public interface ILoginDao {
    /**
     * 登录
     * @param loginName
     * @param password
     * @return
     * @throws AppException
     */
    U_Staff findStaffByNamePassword(String loginName, String password) throws AppException;

    /**
     * 保存redis中
     * @param token
     * @param outInfo
     * @throws AppException
     */
    void saveToken(String token, VOoutInfo outInfo) throws AppException;

    /**
     * 查询token ,返回
     * @param loginTuken
     * @return
     * @throws AppException
     */

    String getTokenIsValid(String loginTuken)throws AppException;
}
