package com.luke.web.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class S_Space extends _M {
    /**名称*/
    @Column(length = 60)
    String name ;

    /**名称拼音*/
    @Column(length = 100)
    String py_name ;

    /**地址*/
    @Column(length = 100)
    String addr ;

    /**电话*/
    @Column(length = 13)
    String tel ;

    /**负责人*/
    @ManyToOne
    @JoinColumn(name = "staffId")
    U_Staff staff ;

    /**上级单位*/
    Long fatherId ;

    @Transient
    List<S_Space> child ;

    public List<S_Space> getChild() {
        return child;
    }

    public void setChild(List<S_Space> child) {
        this.child = child;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPy_name() {
        return py_name;
    }

    public void setPy_name(String py_name) {
        this.py_name = py_name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public U_Staff getStaff() {
        return staff;
    }

    public void setStaff(U_Staff staff) {
        this.staff = staff;
    }

    public Long getUpSpace() {
        return fatherId;
    }

    public void setUpSpace(Long fatherId) {
        this.fatherId = fatherId;
    }
}
