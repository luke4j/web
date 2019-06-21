package com.luke.web.vo.login;


import com.luke.web.tool.vo.VOOut;

public class VOoutLogin extends VOOut {

    /**登录指令牌*/
    String token ;
    /**登录人ID*/
    String staffId ;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}
