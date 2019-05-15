[TOC]

# 开发说明
## 命名规则
``在java 或 js中，以拼音命名的变量以p_开头``

``在js 中 jquery对象以$开头 ，backbone对象以b_``

``html元素id命名以元素类型+name命名 比如 <input id="ipt_name" name="name" />``

``html元素id命名以元素类型+功能命名 比如 <button id="btn_login">``

## 模块关系和功能介绍

``
model模块是： 实体类（Entity）与前后台传参类(VO)
``

``
repo模块是：数据库连接类，比如 hibernate 的 session之类的 ,数据连接池的配置
``

``
js模块是：测试学习用到的js组件用  比如backbone  underscore layui jquery  这里有相关的文档与api
``

``
applogin 模块是登录模块，会返回用户信息，用户角色，用户权限 ，系统时间，session保存用户的前台与后台，applogin依赖repo依赖model
``












