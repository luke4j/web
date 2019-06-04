package com.luke.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Sys_Yw_km extends _M  {

    @Column(length = 40)
    String bm  ;

    /**业务说明*/
    @Column(length = 100)
    String info ;
    /**操作商品类型，正，次，残*/
    @Column(length = 6)
    String kind ;
    /**显示名称*/
    @Column(length = 40)
    String name ;
    /**对数据记录的操作类型，+ ，-*/
    @Column(length = 6)
    String opt ;

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }
}
