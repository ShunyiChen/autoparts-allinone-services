package com.shunyi.autoparts.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/** 权限 */
@Entity
@Table(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "permission")
    protected Set<RolePermissionMapping> userRoleUserRolePermissionSet = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Set<RolePermissionMapping> getUserRoleUserRolePermissionSet() {
        return userRoleUserRolePermissionSet;
    }

    public void setUserRoleUserRolePermissionSet(Set<RolePermissionMapping> userRoleUserRolePermissionSet) {
        this.userRoleUserRolePermissionSet = userRoleUserRolePermissionSet;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", userRoleUserRolePermissionSet=" + userRoleUserRolePermissionSet +
                '}';
    }
}
