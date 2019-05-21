package com.luke.web.login.dao;

import com.luke.web.model.U_Staff;
import com.luke.web.tool.exception.AppException;
import com.luke.web.vo.login.VOInUpdatePwd;
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

    /**
     * 登出访求
     * @param loginTuken
     * @throws AppException
     */
    void delToken(String loginTuken)throws AppException;

    /**
     * 修改个人密码
     * @param vo
     * @return
     * @throws AppException
     */
    U_Staff getStaff(VOInUpdatePwd vo)throws AppException;
}
