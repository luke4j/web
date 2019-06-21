package com.luke.web.vo.login;

import com.luke.web.model.Lgn_Msg;
import com.luke.web.tool.vo.VOOut;

public class VOoutMsg extends VOOut {


    public VOoutMsg() {}

    public VOoutMsg(Lgn_Msg msg){
        this.id = msg.getId() ;
        this.title = msg.getC_title() ;
        this.content = msg.getC_content() ;
        this.read = msg.getC_read() ;
        this.type = msg.getC_type() ;
    }

    Long id ;
    /**消息标题*/
    String title ;
    /**消息内家*/
    String content ;
    String type  ;
    /**是否已读*/
    Boolean read ;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }
}
