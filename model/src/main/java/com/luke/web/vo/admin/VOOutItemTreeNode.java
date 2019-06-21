package com.luke.web.vo.admin;


import com.luke.web.tool.vo.VOOut;

import java.util.ArrayList;
import java.util.List;

public class VOOutItemTreeNode extends VOOut {

    public VOOutItemTreeNode (){} ;

    public VOOutItemTreeNode(Long id, String title,String src ,Long fid,Integer p_bm,String tip) {
        this.id = id;
        this.title = title;
        this.src = src ;
        this.fid = fid ;
        this.p_bm = p_bm ;
        this.tip = tip ;
    }

    private Long id ;
    private String title ;
    private String src ;
    private Long fid ;
    private Integer p_bm ;
    private String tip ;

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Integer getP_bm() {
        return p_bm;
    }

    public void setP_bm(Integer p_bm) {
        this.p_bm = p_bm;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

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
