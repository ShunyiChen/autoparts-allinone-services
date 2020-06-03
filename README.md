# autoparts-services-allinone

### Java8 + SpringBoot2 + RestAPI + JPA + PostgreSQL +SpringSecurety + JWT





系统初始化数据生成：
在postmand的Runner执行Startup文件夹内APIs

初始化步骤：
1.创建root用户
2.插入permission集合
3.创建一个Warehouse
4.创建一个Store
5.创建一个根SupplierCategory
6.创建一个根ConsumerCategory
7.创建一个根VFSCategory


构建：
mvn clean package

运行：
mvn spring-boot:run

快捷运行配置：
windows环境下，将win目录加到path环境变量中，执行run启动


与swagger2整合
1.http://localhost:8080/swagger-ui.html
2.执行/authenticate取token
3.设置全局token(token以Bearer 开头)