package com.shunyi.autoparts.model;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/** 角色 */
@Entity
@Table(name = "roles")
public class Role {
    /** 角色ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 角色名称 */
    @Column(name = "name")
    private String name;

    /** 描述 */
    @Column(name = "description")
    private String description;

    /** 用户与角色映射关系 */
    @OneToMany(mappedBy = "role")
    protected Set<UserRoleMapping> userRoleMappingSet = new HashSet<>();

    /** 角色与权限映射关系 */
    @OneToMany(mappedBy = "role")
    protected Set<RolePermissionMapping> rolePermissionMappingSet = new HashSet<>();

    public Role() {
    }

    public Role(String name, String description, Set<UserRoleMapping> userRoleMappingSet, Set<RolePermissionMapping> rolePermissionMappingSet) {
        this.name = name;
        this.description = description;
        this.userRoleMappingSet = userRoleMappingSet;
        this.rolePermissionMappingSet = rolePermissionMappingSet;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserRoleMapping> getUserRoleMappingSet() {
        return userRoleMappingSet;
    }

    public void setUserRoleMappingSet(Set<UserRoleMapping> userRoleMappingSet) {
        this.userRoleMappingSet = userRoleMappingSet;
    }

    public Set<RolePermissionMapping> getRolePermissionMappingSet() {
        return rolePermissionMappingSet;
    }

    public void setRolePermissionMappingSet(Set<RolePermissionMapping> rolePermissionMappingSet) {
        this.rolePermissionMappingSet = rolePermissionMappingSet;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", userRoleMappingSet=" + userRoleMappingSet +
                ", rolePermissionMappingSet=" + rolePermissionMappingSet +
                '}';
    }
}
