package com.luke.web.admin.controller.impl;

import com.luke.web.admin.controller.IAdminController;
import com.luke.web.admin.service.IAdminService;
import com.luke.web.tool.exception.AppException;
import com.luke.web.tool.web.ActResult;
import com.luke.web.vo.admin.VOInItemTreeNode;
import com.luke.web.vo.admin.VOOutItemTreeNode;
import com.luke.web.vo.login.VOInLogin;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
public class AdminController implements IAdminController {

    @Resource
    IAdminService adminService ;

    @Override
    public ActResult<VOOutItemTreeNode> findAllItemTreeNode(HttpServletRequest request, HttpServletResponse response,
                                                            ActResult<VOOutItemTreeNode> actResult,
                                                            @Valid VOInLogin vo, BindingResult result) throws AppException {
        actResult.setDoing("管理员管理程序功能");
        VOOutItemTreeNode rootNode = this.adminService.findAllItemTreeNode(vo);
        actResult.setData(rootNode);
        return actResult;
    }

    @Override
    public ActResult<VOOutItemTreeNode> editTreeNode(HttpServletRequest request, HttpServletResponse response,
                                                     ActResult<VOOutItemTreeNode> actResult,
                                                     @Valid VOInItemTreeNode vo, BindingResult result) throws AppException {
        actResult.setDoing("管理员修改程序功能");
        VOOutItemTreeNode rootNode = this.adminService.editTreeNode(vo);
        actResult.setData(rootNode);
        return actResult;
    }

    @Override
    public ActResult<VOOutItemTreeNode> addTreeNode(HttpServletRequest request, HttpServletResponse response,
                                                    ActResult<VOOutItemTreeNode> actResult,
                                                    @Valid VOInItemTreeNode vo, BindingResult result) throws AppException {
        actResult.setDoing("管理员添加程序功能");
        VOOutItemTreeNode rootNode = this.adminService.addTreeNode(vo);
        actResult.setData(rootNode);
        return actResult;
    }
}
