package com.luke.web.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Lgn_Role extends _M{

    /**角色名*/
    @Column(length = 20)
    String name ;

    /**角色对应的权限*/
    @ManyToMany
    @JoinTable(name = "lgn_role_item",
            joinColumns = @JoinColumn(name = "roleId"),
            inverseJoinColumns = @JoinColumn(name = "itemId")
    )
    List<Lgn_Item> srcs ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lgn_Item> getSrcs() {
        return srcs;
    }

    public void setSrcs(List<Lgn_Item> srcs) {
        this.srcs = srcs;
    }
}
