package com.luke.web.login.dao.impl;

import com.luke.web.login.dao.ILoginDao;
import com.luke.web.model.U_Staff;
import com.luke.web.repo.dao.DBDao;
import com.luke.web.tool.LKMap;
import com.luke.web.tool.exception.AppException;
import org.springframework.stereotype.Component;

@Component
public class LoginDao extends DBDao implements ILoginDao  {

    @Override
    public U_Staff findStaffByNamePassword(String loginName, String password) throws AppException {
        U_Staff u_staff = this.getUnique("From U_Staff u where u.loginName=:loginName and u.password=:password",
                new LKMap<String,Object>().put1("loginName",loginName).put1("password",password)) ;
        return u_staff;
    }
}
