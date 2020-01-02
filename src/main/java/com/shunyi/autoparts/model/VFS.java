package com.shunyi.autoparts.model;

import javax.persistence.*;

/** 系统文件系统表 */
@Entity
@Table(name = "vfs")
public class VFS {
    /** 自增长ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 名称 */
    private String name;
    /** 协议 */
    private String protocol;
    /** 主机地址 */
    private String host;
    /** 端口 */
    private Integer port;
    /** 用户主目录 */
    private String home;
    /** 连接用户名 */
    private String userName;
    /** 连接密码 */
    private String password;
    /** 可读 */
    private Boolean canRead;
    /** 可写 */
    private Boolean canWrite;
    /** VFS类目ID */
    private Long categoryId;

    public VFS() {
    }

    public VFS(String name, String protocol, String host, Integer port, String home, String userName, String password, Boolean canRead, Boolean canWrite, Long categoryId) {
        this.name = name;
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.home = home;
        this.userName = userName;
        this.password = password;
        this.canRead = canRead;
        this.canWrite = canWrite;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getCanRead() {
        return canRead;
    }

    public void setCanRead(Boolean canRead) {
        this.canRead = canRead;
    }

    public Boolean getCanWrite() {
        return canWrite;
    }

    public void setCanWrite(Boolean canWrite) {
        this.canWrite = canWrite;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "VFS{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", protocol='" + protocol + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", home='" + home + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", canRead=" + canRead +
                ", canWrite=" + canWrite +
                ", categoryId=" + categoryId +
                '}';
    }
}
