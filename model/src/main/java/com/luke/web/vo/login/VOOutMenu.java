package com.luke.web.vo.login;


import com.luke.web.tool.vo.VOOut;

import java.util.ArrayList;
import java.util.List;

public class VOOutMenu extends VOOut {


    public VOOutMenu() {
    }

    public VOOutMenu(String jsurl, String text, String tip,Long fatherId) {
        this.jsurl = jsurl;
        this.text = text;
        this.tip = tip;
        this.fatherId = fatherId ;

    }
    Long fatherId ;
    String jsurl ;
    String text ;
    String tip ;

    public Long getFatherId() {
        return fatherId;
    }

    public void setFatherId(Long fatherId) {
        this.fatherId = fatherId;
    }

    List<VOOutMenu> child = new ArrayList<VOOutMenu>(100) ;

    public String getJsurl() {
        return jsurl;
    }

    public void setJsurl(String jsurl) {
        this.jsurl = jsurl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public List<VOOutMenu> getChild() {
        return child;
    }

    public void setChild(List<VOOutMenu> child) {
        this.child = child;
    }
}
