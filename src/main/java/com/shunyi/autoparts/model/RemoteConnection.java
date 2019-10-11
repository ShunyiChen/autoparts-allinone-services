package com.shunyi.autoparts.model;

import javax.persistence.*;

/** 远程连接表 */
@Entity
@Table(name = "remote_connections")
public class RemoteConnection {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 用户ID */
    private Long userId;
    /** 连接名称 */
    private String name;
    /** 协议 */
    private String protocol;
    /** 主机名 */
    private String hostName;
    /** 端口  */
    private String port;
    /** 是否默认的 */
    private Boolean _default;
    /** 顺序 */
    private Integer aOrder;

    public RemoteConnection() {}

    public RemoteConnection(Long userId, String name, String protocol, String hostName, String port, Boolean _default, Integer aOrder) {
        this.userId = userId;
        this.name = name;
        this.protocol = protocol;
        this.hostName = hostName;
        this.port = port;
        this._default = _default;
        this.aOrder = aOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Boolean get_default() {
        return _default;
    }

    public void set_default(Boolean _default) {
        this._default = _default;
    }

    public Integer getaOrder() {
        return aOrder;
    }

    public void setaOrder(Integer aOrder) {
        this.aOrder = aOrder;
    }

    @Override
    public String toString() {
        return "RemoteConnection{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", protocol='" + protocol + '\'' +
                ", hostName='" + hostName + '\'' +
                ", port='" + port + '\'' +
                ", _default=" + _default +
                ", aOrder=" + aOrder +
                '}';
    }
}
