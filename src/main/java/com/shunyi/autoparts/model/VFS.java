package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @description 虚拟文件系统
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name = "vfs")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
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
    private Boolean readable;
    /** 可写 */
    private Boolean writable;
    /** VFS类目ID */
    private Long categoryId;
    /** 默认的 */
    private Boolean master;
}
