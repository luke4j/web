[TOC]

# 开发说明

## 模块关系

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










