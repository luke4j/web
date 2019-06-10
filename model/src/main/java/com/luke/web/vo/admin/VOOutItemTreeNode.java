package com.luke.web.vo.admin;

import com.luke.web.vo.VOOut;

import java.util.ArrayList;
import java.util.List;

public class VOOutItemTreeNode extends VOOut {

    public VOOutItemTreeNode (){} ;

    public VOOutItemTreeNode(Long id, String title,String src ) {
        this.id = id;
        this.title = title;
        this.src = src ;
    }

    private Long id ;
    private String title ;
    private String src ;


    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    private List<VOOutItemTreeNode> children = new ArrayList<VOOutItemTreeNode>(40);

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

    public List<VOOutItemTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<VOOutItemTreeNode> children) {
        this.children = children;
    }
}
