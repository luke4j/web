[TOC]

ssh连接时使用公匙可能是有时间限制

参数
https://baijiahao.baidu.com/s?id=1606669351803311743&wfr=spider&for=pc

主要修改方法：
删除  用户文件夹下.ssh中的文件

然后使用git命令再生成一次公匙

ssh-keygen -t rsa -C "邮箱地址"

再把公匙内容复制到github  settings/keys中


