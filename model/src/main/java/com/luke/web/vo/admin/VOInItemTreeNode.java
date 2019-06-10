package com.luke.web.vo.admin;

public class VOInItemTreeNode {
    private Long id ;
    private String title ;
    private String src ;
    private Long fid ;
    private Integer p_bm ;
    private String tip ;

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Integer getP_bm() {
        return p_bm;
    }

    public void setP_bm(Integer p_bm) {
        this.p_bm = p_bm;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
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

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }
}
