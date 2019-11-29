Postgres：

//拉镜像
docker pull postgres
//运行容器
docker run --name postgres -e POSTGRES_PASSWORD=cDe3@wsx -p 54321:5432 -d postgres:latest
//进入容器
docker exec -it cd8f133b12f8 bash
//用psql连接数据库
psql -h localhost -U postgres -W cDe3@wsx -p 5432 -d postgres
//切换schema
set search_path to public

//sql常用命令
1.列出数据库 /l
2.列出schema /dn
3.列出数据表 /dt


MySQL:
//拉镜像
docker pull mysql
//启动容器
docker run -d -e MYSQL_ROOT_PASSWORD=cDe3@wsx -p 33061:3306 --privileged=true --name mysql mysql
//进入容器
docker exec -it mysql bash
//连接mysql
mysql -uroot -p

//常用sql
CREATE DATABASE autoparts;
CREATE DATABASE IF NOT EXISTS autoparts default charset utf8 COLLATE utf8_general_ci;