package com.luke.web.login.dao;

import com.luke.web.model.U_Staff;
import com.luke.web.tool.exception.AppException;

public interface ILoginDao {
    /**
     * 登录
     * @param loginName
     * @param password
     * @return
     * @throws AppException
     */
    U_Staff findStaffByNamePassword(String loginName, String password) throws AppException;
}
