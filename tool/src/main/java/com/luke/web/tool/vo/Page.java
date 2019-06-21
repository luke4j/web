package com.luke.web.tool.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by luke on 2018/11/1.
 */
public class Page extends VOIn {

    @ApiModelProperty(name = "数据总条数")
    private Long count ;
    @ApiModelProperty(name = "每页第一条编号")
    private Integer start = 0;
    @ApiModelProperty(name = "第页显示条数")
    private Integer limit = 10 ;


    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
