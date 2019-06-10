package com.luke.web.admin.dao.impl;

import com.luke.web.admin.dao.IAdminDao;
import com.luke.web.model.Lgn_Item;
import com.luke.web.repo.dao.DBDao;
import com.luke.web.tool.exception.AppException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminDao  extends DBDao implements IAdminDao {

    @Override
    public List<Lgn_Item> findAllItemTreeNode() throws AppException {
        return  this.find("From Lgn_Item i ") ;
    }


}
