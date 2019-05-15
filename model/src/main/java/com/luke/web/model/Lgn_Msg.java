package com.luke.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Lgn_Msg extends _M {

    /**消息标题*/
    @Column(length = 20)
    String c_title ;
    /**内容*/
    @Column(length = 100)
    String c_content ;
    /**是否已读*/
    Boolean c_read ;
    /**类型*/
    @Column(length = 8)
    String c_type ;

    /**这是谁的消息*/
    @ManyToOne
    @JoinColumn(name = "staffId")
    U_Staff staff;



    public U_Staff getStaff() {
        return staff;
    }

    public void setStaff(U_Staff staff) {
        this.staff = staff;
    }

    public String getC_title() {
        return c_title;
    }

    public void setC_title(String c_title) {
        this.c_title = c_title;
    }

    public String getC_content() {
        return c_content;
    }

    public void setC_content(String c_content) {
        this.c_content = c_content;
    }

    public Boolean getC_read() {
        return c_read;
    }

    public void setC_read(Boolean c_read) {
        this.c_read = c_read;
    }

    public String getC_type() {
        return c_type;
    }

    public void setC_type(String c_type) {
        this.c_type = c_type;
    }
}
