package com.luke.web.admin.controller;

import com.luke.web.tool.exception.AppException;
import com.luke.web.tool.web.ActResult;
import com.luke.web.vo.admin.VOInItemTreeNode;
import com.luke.web.vo.admin.VOOutItemTreeNode;
import com.luke.web.vo.login.VOInLogin;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequestMapping(path = "/admin")
public interface IAdminController {

    @RequestMapping(path = "findAllItemTreeNode.act",method = RequestMethod.POST)
    ActResult<VOOutItemTreeNode> findAllItemTreeNode(HttpServletRequest request, HttpServletResponse response,
                                                     ActResult<VOOutItemTreeNode> actResult,
                                                     @Valid VOInLogin  vo , BindingResult result) throws AppException ;

    @RequestMapping(path = "editTreeNode.act",method = RequestMethod.POST)
    ActResult<VOOutItemTreeNode> editTreeNode(HttpServletRequest request, HttpServletResponse response,
                                              ActResult<VOOutItemTreeNode> actResult,
                                              @Valid VOInItemTreeNode vo , BindingResult result) throws AppException ;

    @RequestMapping(path = "delTreeNode.act",method = RequestMethod.POST)
    ActResult<VOOutItemTreeNode> delTreeNode(HttpServletRequest request, HttpServletResponse response,
                                              ActResult<VOOutItemTreeNode> actResult,
                                              @Valid VOInItemTreeNode vo , BindingResult result) throws AppException ;

}
