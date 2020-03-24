//For Postgres
insert into permissions(name,description,code) values('销售试图','销售试图访问权限',1);
insert into permissions(name,description,code) values('采购明细','采购明细访问权限',2);
insert into permissions(name,description,code) values('采购退货明细','采购退货明细访问权限',3);
insert into permissions(name,description,code) values('销售明细','销售单访问权限',4);
insert into permissions(name,description,code) values('销售退货明细','销售退货明细访问权限',5);
insert into permissions(name,description,code) values('供应商明细','供应商明细访问权限',6);
insert into permissions(name,description,code) values('库存明细','库存明细访问权限',7);
insert into permissions(name,description,code) values('配件明细','配件明细访问权限',8);
insert into permissions(name,description,code) values('系统设置','系统设置访问权限',9);




//For Mysql, 在IDEA数据库插件里执行执行以下SQL语句，或者在docker容器中执行sql脚本文件因为控制台不显示中文
insert into permissions(name,description,code) values
('销售试图','销售试图访问权限',1);
('采购明细','采购明细访问权限',2);
('采购退货明细','采购退货明细访问权限',3);
('销售明细','销售单访问权限',4);
('销售退货明细','销售退货明细访问权限',5);
('供应商明细','供应商明细访问权限',6);
('库存明细','库存明细访问权限',7);
('配件明细','配件明细访问权限',8);
('系统设置','系统设置访问权限',9);