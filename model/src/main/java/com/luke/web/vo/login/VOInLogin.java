package com.luke.web.vo.login;

import com.luke.web.vo._V;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

@ApiModel
public class VOInLogin implements _V {

    @ApiModelProperty(value = "登录名" ,required = true)
    @NotEmpty(message = "登录名不能为空")
    String loginName ;
    @NotEmpty(message = "登录密码不能为空")
    String password ;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
