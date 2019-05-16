package com.luke.web.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by luke on 2018/11/16.
 */
public class VOInId extends VOIn {

    @ApiModelProperty(name = "id参数")
    Long id ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
