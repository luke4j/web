

-- 登录名admin  登录密码admin
insert u_staff(b_is_del,b_wtime,brithday,login_name,name,password,sex,role_id) values(false,now(),'1984-01-01','admin','于洋','21232f297a57a5a743894a0e4a801fc3','男',1);

delete from lgn_role ;
insert into lgn_role (id,b_is_del,b_wtime,name)values(1,false,now(),'admin') ;

delete from lgn_item ;
insert into lgn_item(id,b_is_del,b_wtime,c_text,p_bm,src,tip,father_id)values
(1,false,now(),'基础数据',100,'','基础数据',0),
(2,false,now(),'商品信息',101,'app/goods/writeInfo','商品信息录入',1),
(3,false,now(),'人员信息',102,'app/staff/writeInfo','人员信息录入',1),
(4,false,now(),'站点信息',103,'app/space/writeInfo','站点信息录入',1) ,
(5,false,now(),'资产登记',200,'','资产登记',0),
(6,false,now(),'库存登记',200,'','库存登记',5),
(7,false,now(),'库存盘点',200,'','库存盘点',5),
(8,false,now(),'程序管理',100,'','程序管理',0),
(9,false,now(),'功能注册',100,'app/admin/admin','功能注册',8) ;
select * from lgn_item  order by id;


delete from lgn_role_item ;
insert into lgn_role_item (role_id,item_id) values
(1,1) ,
(1,2) ,
(1,3) ,
(1,4),
(1,5),
(1,6),
(1,7),
(1,8),
(1,9)

;
