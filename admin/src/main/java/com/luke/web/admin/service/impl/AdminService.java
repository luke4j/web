package com.luke.web.admin.service.impl;

import com.luke.web.admin.dao.IAdminDao;
import com.luke.web.admin.service.IAdminService;
import com.luke.web.model.Lgn_Item;
import com.luke.web.tool.exception.AppException;
import com.luke.web.vo.admin.VOInItemTreeNode;
import com.luke.web.vo.admin.VOOutItemTreeNode;
import com.luke.web.vo.login.VOInLogin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminService implements IAdminService {

    @Resource
    IAdminDao adminDao ;

    @Override
    public VOOutItemTreeNode findAllItemTreeNode(VOInLogin vo) throws AppException {
        List<Lgn_Item> lst = this.adminDao.findAllItemTreeNode() ;
        VOOutItemTreeNode rootNode = new VOOutItemTreeNode() ;
        rootNode.setId(0l);
        rootNode.setTitle("Root");
        this.rootNode(rootNode,lst) ;
        return rootNode;
    }
    private void rootNode(VOOutItemTreeNode rootNode ,List<Lgn_Item> lst ){
        for(Lgn_Item item :lst){
            if(item.getFatherId().longValue()==rootNode.getId().longValue()){
                VOOutItemTreeNode c = new VOOutItemTreeNode(item.getId(),item.getC_text(),item.getSrc()) ;
                rootNode.getChildren().add(c) ;
                rootNode(c,lst) ;
            }
        }
    }

    @Override
    public VOOutItemTreeNode editTreeNode(VOInItemTreeNode vo) throws AppException {
        return null;
    }

    @Override
    public VOOutItemTreeNode addTreeNode(VOInItemTreeNode vo) throws AppException {
        return null;
    }
}
