package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @description 权限
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name = "permissions")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Permission {
    /** 权限ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /** 权限名称 */
    @Column(name = "name")
    private String name;
    /** 描述 */
    @Column(name = "description")
    private String description;
    /** 角色与权限映射关系 */
    @OneToMany
    @JoinColumn(name = "permission_role_id",
            foreignKey = @ForeignKey(name = "PERMISSION_ROLE_ID_FK")
    )
    protected Set<RolePermissionMapping> rolePermissionMappingSet = new HashSet<>();
    /** 权限编码 */
    @Column(name = "code")
    private Integer code;
}
