package com.luke.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by luke on 2018/11/2.
 */
@ApiModel
public class VOOut implements VO {
    @ApiModelProperty(name = "用户目标地址")
    private String _srcUrl;

    @ApiModelProperty(name = "数据中的传回信息")
    String _info;

    public String get_srcUrl() {
        return _srcUrl;
    }

    public void set_srcUrl(String _srcUrl) {
        this._srcUrl = _srcUrl;
    }

    public String get_info() {
        return _info;
    }

    public void set_info(String _info) {
        this._info = _info;
    }
}
