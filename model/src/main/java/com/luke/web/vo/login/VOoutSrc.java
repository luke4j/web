package com.luke.web.vo.login;

import java.util.List;

public class VOoutSrc {

    /**功能名*/
    String c_text ;
    /**需要加载的程序入口文件*/
    String src ;
    Long id ;
    /**提示说明*/
    String tip ;
    /**下一级功能*/
    List<VOoutSrc> child ;

    public String getC_text() {
        return c_text;
    }

    public void setC_text(String c_text) {
        this.c_text = c_text;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public List<VOoutSrc> getChild() {
        return child;
    }

    public void setChild(List<VOoutSrc> child) {
        this.child = child;
    }
}
