package com.luke.web.vo;


import io.swagger.annotations.ApiModelProperty;

/**
 * Created by luke on 2018/10/31.
 */
public class VORedisUser extends VOOut {

    @ApiModelProperty(value = "登录标志存在redis缓存，存在8*60*60秒")
    private String loginTuken ;

    @ApiModelProperty(value = "user id")
    private Long id ;

    @ApiModelProperty(value = "用户名")
    private String name ;

    @ApiModelProperty(value = "登录名")
    private String loginName ;

    @ApiModelProperty(value = "登录站点名")
    private String com_name ;

    @ApiModelProperty(value = "登录站点Id")
    private Long loginComId ;


    public String getLoginTuken() {
        return loginTuken;
    }

    public void setLoginTuken(String loginTuken) {
        this.loginTuken = loginTuken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getCom_name() {
        return com_name;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name;
    }

    public Long getLoginComId() {
        return loginComId;
    }

    public void setLoginComId(Long loginComId) {
        this.loginComId = loginComId;
    }
}
