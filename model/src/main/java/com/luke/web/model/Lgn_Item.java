package com.luke.web.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Lgn_Item extends _M{

    /**编号*/
    Integer p_bm ;
    /**功能名*/
    @Column(length = 20)
    String c_text ;
    /**功能入口程序文件*/
    @Column(length = 200)
    String src ;
    /**提示说明*/
    @Column(length = 100)
    String tip ;

    @ManyToOne
    @JoinColumn(name = "fatherId")
    Lgn_Item father ;

    @OneToMany(mappedBy = "father")
    List<Lgn_Item> child ;

    public Integer getP_bm() {
        return p_bm;
    }

    public void setP_bm(Integer p_bm) {
        this.p_bm = p_bm;
    }

    public List<Lgn_Item> getChild() {
        return child;
    }

    public void setChild(List<Lgn_Item> child) {
        this.child = child;
    }

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

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Lgn_Item getFather() {
        return father;
    }

    public void setFather(Lgn_Item father) {
        this.father = father;
    }
}
