package com.luke.web.admin.service.impl;

import com.luke.web.admin.dao.IAdminDao;
import com.luke.web.admin.service.IAdminService;
import com.luke.web.model.Lgn_Item;
import com.luke.web.tool.LK;
import com.luke.web.tool.exception.AppException;
import com.luke.web.vo.admin.VOInItemTreeNode;
import com.luke.web.vo.admin.VOOutItemTreeNode;
import com.luke.web.vo.login.VOInLogin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
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
                VOOutItemTreeNode c = new VOOutItemTreeNode(item.getId(),item.getC_text(),item.getSrc(),item.getFatherId(),item.getP_bm(),item.getTip()) ;
                rootNode.getChildren().add(c) ;
                rootNode(c,lst) ;
            }
        }
    }

    @Override
    public VOOutItemTreeNode editTreeNode(VOInItemTreeNode vo) throws AppException {
        if(LK.ObjIsNull(vo.getId())&&LK.ObjIsNotNull(vo.getFid())){
            Lgn_Item item = new Lgn_Item() ;
            item.setC_text(vo.getTitle());
            item.setFatherId(vo.getFid());
            item.setSrc(vo.getSrc());
            item.setP_bm(vo.getP_bm());
            item.setTip(vo.getTip());
            this.adminDao.save(item) ;
        }else if (LK.ObjIsNotNull(vo.getId())){
            Lgn_Item item = this.adminDao.get(Lgn_Item.class,vo.getId()) ;
            item.setC_text(vo.getTitle());
            item.setFatherId(vo.getFid());
            item.setSrc(vo.getSrc());
            item.setP_bm(vo.getP_bm());
            item.setTip(vo.getTip());
        }
        return this.findAllItemTreeNode(null);
    }

    @Override
    public VOOutItemTreeNode addTreeNode(VOInItemTreeNode vo) throws AppException {
        return null;
    }
}
