package com.luke.web.vo.login;

import com.luke.web.model.Lgn_Item;
import com.luke.web.vo.VOOut;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class VOoutSrc extends VOOut {

    public VOoutSrc (){}
    public VOoutSrc(Lgn_Item item){
        dg(item,this) ;
    }

    public void dg (Lgn_Item item,VOoutSrc ths){
        BeanUtils.copyProperties(item,ths);
        if(item.getChild()!=null&&item.getChild().size()>0){
            ths.setChild(new ArrayList<VOoutSrc>(item.getChild().size()));
            for(Lgn_Item it :item.getChild()){
                VOoutSrc src = new VOoutSrc();
                ths.getChild().add(src) ;
                dg(it,src) ;
            }
        }
    }

    /**功能名*/
    String c_text ;
    /**需要加载的程序入口文件*/
    String src ;
    Long id ;
    /**提示说明*/
    String tip ;
    /**下一级功能*/
    List<VOoutSrc> child ;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public List<VOoutSrc> getChild() {
        return child;
    }

    public void setChild(List<VOoutSrc> child) {
        this.child = child;
    }
}
