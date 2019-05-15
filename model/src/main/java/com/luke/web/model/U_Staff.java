package com.luke.web.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class U_Staff extends _M{
    /**姓名*/
    @Column(length = 10)
    String name ;

    /**密码*/
    @Column(length = 40)
    String password ;
    /**登录名*/
    @Column(length = 40)
    String loginName ;

    /**性别*/
    @Column(length = 2)
    String sex ;
    /**生日*/
    Date brithday ;


    /**电话*/
    @Column(length = 15)
    String phone ;

    /**学历*/
    @Column(length = 20)
    String p_xl ;
    /**职务*/
    @Column(length = 20)
    String p_zw ;

    @OneToMany(mappedBy = "staff")
    List<Lgn_Msg> msgs ;

    /**默认单位*/
    @ManyToOne
    @JoinColumn(name = "spaceId")
    S_Space defSpace ;

    /**角色*/
    @ManyToOne
    @JoinColumn(name = "roleId")
    Lgn_Role role ;


    public List<Lgn_Msg> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<Lgn_Msg> msgs) {
        this.msgs = msgs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getP_xl() {
        return p_xl;
    }

    public void setP_xl(String p_xl) {
        this.p_xl = p_xl;
    }

    public String getP_zw() {
        return p_zw;
    }

    public void setP_zw(String p_zw) {
        this.p_zw = p_zw;
    }

    public S_Space getDefSpace() {
        return defSpace;
    }

    public void setDefSpace(S_Space defSpace) {
        this.defSpace = defSpace;
    }

    public Lgn_Role getRole() {
        return role;
    }

    public void setRole(Lgn_Role role) {
        this.role = role;
    }
}
