package com.luke.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Sys_Cw_km  extends _M{
    @Column(length = 50)
    String bm ;
    @Column(length = 100)
    String name ;
    /**是会计科目的哪一类*/
    @Column(length = 20)
    String c_kind ;

    /**默认使用*/
    @Column(length = 100)
    String c_use ;
    /**备注*/
    @Column(length = 200)
    String bz ;

    Long fatherId ;

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getC_kind() {
        return c_kind;
    }

    public void setC_kind(String c_kind) {
        this.c_kind = c_kind;
    }

    public String getC_use() {
        return c_use;
    }

    public void setC_use(String c_use) {
        this.c_use = c_use;
    }

    public Long getFatherId() {
        return fatherId;
    }

    public void setFatherId(Long fatherId) {
        this.fatherId = fatherId;
    }
}
