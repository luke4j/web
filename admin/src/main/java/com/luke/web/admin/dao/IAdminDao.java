package com.luke.web.admin.dao;

import com.luke.web.model.Lgn_Item;
import com.luke.web.tool.exception.AppException;

import java.util.List;

public interface IAdminDao {
    List<Lgn_Item> findAllItemTreeNode() throws AppException;
}
