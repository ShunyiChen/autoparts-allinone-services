package com.shunyi.autoparts.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/** 权限 */
@Entity
@Table(name = "permissions")
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
    @OneToMany(mappedBy = "permission")
    protected Set<RolePermissionMapping> rolePermissionMappingSet = new HashSet<>();

    /** 权限编码 */
    @Column(name = "code")
    private Integer code;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<RolePermissionMapping> getRolePermissionMappingSet() {
        return rolePermissionMappingSet;
    }

    public void setRolePermissionMappingSet(Set<RolePermissionMapping> rolePermissionMappingSet) {
        this.rolePermissionMappingSet = rolePermissionMappingSet;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rolePermissionMappingSet=" + rolePermissionMappingSet +
                ", code='" + code + '\'' +
                '}';
    }
}
