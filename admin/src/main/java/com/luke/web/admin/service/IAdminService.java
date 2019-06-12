package com.luke.web.admin.service;

import com.luke.web.tool.exception.AppException;
import com.luke.web.vo.admin.VOInItemTreeNode;
import com.luke.web.vo.admin.VOOutItemTreeNode;
import com.luke.web.vo.login.VOInLogin;

public interface IAdminService {
    VOOutItemTreeNode findAllItemTreeNode(VOInLogin vo) throws AppException;

    VOOutItemTreeNode editTreeNode(VOInItemTreeNode vo)throws AppException;

    VOOutItemTreeNode delTreeNode(VOInItemTreeNode vo)throws AppException;
}
