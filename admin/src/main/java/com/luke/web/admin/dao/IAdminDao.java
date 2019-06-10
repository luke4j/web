package com.luke.web.admin.dao;

import com.luke.web.model.Lgn_Item;
import com.luke.web.repo.dao.IDBDao;
import com.luke.web.tool.exception.AppException;

import java.util.List;

public interface IAdminDao extends IDBDao {
    List<Lgn_Item> findAllItemTreeNode() throws AppException;
}
