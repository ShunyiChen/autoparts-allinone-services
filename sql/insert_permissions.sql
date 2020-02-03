//For Postgres
insert into permissions(name,description,code) values('主界面','主界面访问权限',1);
insert into permissions(name,description,code) values('购货单','购货单访问权限',2);
insert into permissions(name,description,code) values('购货退货单','购货退货单访问权限',3);
insert into permissions(name,description,code) values('销售单','销售单访问权限',4);
insert into permissions(name,description,code) values('销售退货单','销售退货单访问权限',5);
insert into permissions(name,description,code) values('供应商管理','供应商管理访问权限',6);
insert into permissions(name,description,code) values('库存管理','库存管理访问权限',7);
insert into permissions(name,description,code) values('配件管理','配件管理访问权限',8);
insert into permissions(name,description,code) values('系统维护','系统维护访问权限',9);




//For Mysql, 在IDEA数据库插件里执行执行以下SQL语句，或者在docker容器中执行sql脚本文件因为控制台不显示中文
insert into permissions(name,description,code) values
('主界面','主界面访问权限',1),
('购货单','购货单访问权限',2),
('购货退货单','购货退货单访问权限',3),
('销售单','销售单访问权限',4),
('销售退货单','销售退货单访问权限',5),
('供应商管理','供应商管理访问权限',6),
('库存管理','库存管理访问权限',7),
('配件管理','配件管理访问权限',8),
('系统维护','系统维护访问权限',9);

