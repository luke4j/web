package com.luke.web.login.dao.impl;

import com.luke.web.login.dao.ILoginDao;
import com.luke.web.model.Lgn_Item;
import com.luke.web.model.Lgn_Role;
import com.luke.web.model.U_Staff;
import com.luke.web.repo.dao.DBDao;
import com.luke.web.tool.LK;
import com.luke.web.tool.LKMap;
import com.luke.web.tool.exception.AppException;
import com.luke.web.vo.login.VOInUpdatePwd;
import com.luke.web.vo.login.VOOutMenu;
import com.luke.web.vo.login.VOoutInfo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoginDao extends DBDao implements ILoginDao  {

    @Override
    @Cacheable(value = "staff",key = "#loginName")
    public U_Staff findStaffByNamePassword(String loginName, String password) throws AppException {
        U_Staff u_staff = this.getUnique("From U_Staff u where u.loginName=:loginName and u.password=:password",
                new LKMap<String,Object>().put1("loginName",loginName).put1("password",password)) ;
        return u_staff;
    }

    @Override
//    @CacheEvict(value = "redis-staff",key = "#token")
    public void saveToken(String token, VOoutInfo outInfo) throws AppException {
        this.setRedisValueAndEX(token, LK.ObjToJsonStr(outInfo),60l*4) ;
    }

    @Override
//    @Cacheable(value = "redis-staff",key = "#loginTuken")
    public String getTokenIsValid(String loginTuken) throws AppException {
        String usreInfo = this.getRedisValue(loginTuken) ;
        return usreInfo;
    }

    @Override
//    @CacheEvict(value = "redis-staff",key = "#token")
    public void delToken(String loginTuken) throws AppException {
        this.delRedisValueByKey(loginTuken) ;
    }

    @Override
    @Cacheable(value = "staff",key = "#vo.id")
    public U_Staff getStaff(VOInUpdatePwd vo) throws AppException {
        U_Staff user = this.get(U_Staff.class,vo.getId()) ;
        return user;
    }

    @Override
    public VOOutMenu getRole(Long staffId) throws AppException {
        U_Staff user = this.get(U_Staff.class,staffId) ;
        if(user.getRole()==null)
            return null ;
        user.getRole().getSrcs().size() ;
        VOOutMenu role = new VOOutMenu() ;
        role.setFatherId(0l);
        List<Lgn_Item> allItems =  new ArrayList<Lgn_Item>(user.getRole().getSrcs().size()) ;
        allItems.addAll(user.getRole().getSrcs()) ;
        roleItems(role,allItems) ;
        return role ;
    }
    private void roleItems(VOOutMenu menu,List<Lgn_Item> allItems) {
        for(Lgn_Item item :allItems){
            if(item.getFatherId().longValue()==menu.getFatherId().longValue()){
                VOOutMenu nMenu = new VOOutMenu(item.getSrc(),item.getC_text(),item.getTip(),item.getId()) ;
                menu.getChild().add(nMenu) ;
                roleItems(nMenu,allItems);
            }
        }
    }

}
