package com.luke.web.login.service.impl;

import com.luke.web.login.dao.ILoginDao;
import com.luke.web.login.service.ILoginService;
import com.luke.web.model.Lgn_Item;
import com.luke.web.model.Lgn_Msg;
import com.luke.web.model.U_Staff;
import com.luke.web.tool.LK;
import com.luke.web.tool.exception.AppException;
import com.luke.web.tool.web.ActResult;
import com.luke.web.vo.VOIn;
import com.luke.web.vo.VOOut;
import com.luke.web.vo.login.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@CacheConfig
public class LoginService implements ILoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class) ;


    @Resource
    ILoginDao loginDao ;

    @Override
    public VOoutInfo findLoginUser(VOInLogin vo , ActResult<VOoutInfo> actResult) throws AppException {

        VOoutInfo outInfo = new VOoutInfo() ;
        U_Staff staff = this.loginDao.findStaffByNamePassword(vo.getLoginName(),vo.getPassword()) ;
        if(staff==null){
            throw AppException.create("err-登录-登录失败") ;
        }
        outInfo.setStaffId(staff.getId());
        outInfo.setStaffName(staff.getName());
        outInfo.setP_zhiWei(staff.getP_zw());

        /**设置角色*/
        if(staff.getRole()!=null){
            outInfo.setRoleName(staff.getRole().getName());
        }
        /**设置站点*/
        if(staff.getDefSpace()!=null){
            outInfo.setDefSpaceName(staff.getDefSpace().getName());
            outInfo.setDefSpaceId(staff.getDefSpace().getId());
        }
        /**设置消息*/
        if(staff.getMsgs()!=null&&staff.getMsgs().size()>0){
            for (Lgn_Msg msg:staff.getMsgs() ) {
                outInfo.getMsgs().add(new VOoutMsg(msg)) ;
            }
        }
        /**设置菜单*/
        if(staff.getRole()!=null&&staff.getRole().getSrcs()!=null&&staff.getRole().getSrcs().size()>0){
            for(Lgn_Item item :staff.getRole().getSrcs()){
                outInfo.getSrcs().add(new VOoutSrc(item)) ;
            }
        }
        /**设置redis数据*/
        String token = LK.uuid() ;
        token = "lukeToken-"+token ;
        actResult.getExtend().put("token",token) ;
        this.loginDao.saveToken(token,outInfo) ;
        if(logger.isDebugEnabled()){
            logger.debug("姓名 is "+outInfo.getStaffName()+"====ID is"+outInfo.getStaffId()+"====token is"+token);
        }
        return outInfo;
    }


    @Override
    public VOoutInfo getTokenIsValid(String loginTuken) throws AppException {
        String strTokenVal = this.loginDao.getTokenIsValid(loginTuken) ;
        VOoutInfo tokenVal = LK.StrJson2Obj(strTokenVal,VOoutInfo.class) ;
        return tokenVal;
    }

    @Override
    public void delToken(String loginTuken) throws AppException {
        this.loginDao.delToken(loginTuken) ;
    }

    @Override
    @Transactional
    @CacheEvict(value = "staff")
    public void updatePassword(VOInUpdatePwd vo, ActResult<VOOut> actResult)throws AppException {
        U_Staff user = this.loginDao.getLoginUser(vo.getLoginTuken()) ;
        if(user==null) throw AppException.create("登录","ID查询不到操作者") ;
        if(!user.getPassword().equals(vo.getPassword())) throw AppException.create("登录","原密码不正确") ;
        this.loginDao.delToken(vo.getLoginTuken()) ;

        user.setPassword(vo.getNewPassword());
    }

    @Override
    public VOOutMenu getRole(VOIn vo, ActResult<VOOutMenu> actResult) throws AppException {
        String json = this.loginDao.getTokenIsValid(vo.getLoginTuken()) ;
        VOoutInfo outInfo = LK.StrJson2Obj(json,VOoutInfo.class) ;
        VOOutMenu role = this.loginDao.getRole(outInfo.getStaffId()) ;
        return role;
    }
}
