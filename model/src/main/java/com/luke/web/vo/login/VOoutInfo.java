package com.luke.web.vo.login;

import com.luke.web.vo.VOOut;

import java.util.ArrayList;
import java.util.List;

public class VOoutInfo extends VOOut {

    /**登录人姓名*/
    String staffName ;
    /**登录人ID*/
    Long staffId ;

    /**默认职能单位名*/
    String defSpaceName ;
    /**默认职能单位ID*/
    Long defSpaceId ;
    /**实际职位*/
    String p_zhiWei ;
    /**角色名*/
    String roleName ;
    /**角色具体权限*/
    List<VOoutSrc> srcs = new ArrayList<VOoutSrc>(100);
    /**未读消息*/
    List<VOoutMsg> msgs = new ArrayList<VOoutMsg>(10);

    public String getP_zhiWei() {
        return p_zhiWei;
    }

    public void setP_zhiWei(String p_zhiWei) {
        this.p_zhiWei = p_zhiWei;
    }

    public List<VOoutSrc> getSrcs() {
        return srcs;
    }

    public void setSrcs(List<VOoutSrc> srcs) {
        this.srcs = srcs;
    }

    public Long getDefSpaceId() {
        return defSpaceId;
    }

    public void setDefSpaceId(Long defSpaceId) {
        this.defSpaceId = defSpaceId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDefSpaceName() {
        return defSpaceName;
    }

    public void setDefSpaceName(String defSpaceName) {
        this.defSpaceName = defSpaceName;
    }

    public List<VOoutMsg> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<VOoutMsg> msgs) {
        this.msgs = msgs;
    }
}
