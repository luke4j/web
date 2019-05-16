package com.luke.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by luke on 2018/11/2.
 */
@ApiModel
public class VOOut implements VO {
    @ApiModelProperty(name = "用户目标地址")
    private String srcUrl ;

    public String getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
    }
}
