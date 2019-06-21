package com.luke.web.tool.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by luke on 2018/11/2.
 */
@ApiModel
public class VOIn implements VO {

    @ApiModelProperty(name = "用户目标地址")
    private String srcUrl ;
    @ApiModelProperty(name = "合法用户标识")
    private String loginTuken ;
    @ApiModelProperty(name = "登录站点ID")
    private Long loginComId ;

    public Long getLoginComId() {
        return loginComId;
    }

    public void setLoginComId(Long loginComId) {
        this.loginComId = loginComId;
    }

    public String getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
    }

    public String getLoginTuken() {
        return loginTuken;
    }

    public void setLoginTuken(String loginTuken) {
        this.loginTuken = loginTuken;
    }
}
