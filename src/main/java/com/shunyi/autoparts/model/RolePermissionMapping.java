package com.shunyi.autoparts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/** 角色和权限映射关系 */
@Entity
@Table(name="role_permission_mappings")
@org.hibernate.annotations.Immutable
public class RolePermissionMapping {
    @Embeddable
    public static class Id implements Serializable {

        /** 角色ID */
        @Column(name="role_id")
        protected Long roleId;

        /** 权限ID */
        @Column(name="permission_id")
        protected Long permissionId;

        public Id() {}

        /**
         * Constructor
         *
         * @param roleId 角色ID
         * @param permissionId 权限ID
         */
        public Id(Long roleId, Long permissionId) {
            this.roleId = roleId;
            this.permissionId = permissionId;
        }

        public Long getRoleId() {
            return roleId;
        }

        public void setRoleId(Long roleId) {
            this.roleId = roleId;
        }

        public Long getPermissionId() {
            return permissionId;
        }

        public void setPermissionId(Long permissionId) {
            this.permissionId = permissionId;
        }

        @Override
        public String toString() {
            return "Id{" +
                    "roleId=" + roleId +
                    ", permissionId=" + permissionId +
                    '}';
        }
    }

    @EmbeddedId
    protected Id id = new Id();

    @ManyToOne
    @JoinColumn(
            name = "role_id",
            insertable = false, updatable = false
    )
    @JsonIgnore
    protected Role role;

    @ManyToOne
    @JoinColumn(
            name = "permission_id",
            insertable = false, updatable = false
    )
    @JsonIgnore
    protected Permission permission;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "RolePermissionMapping{" +
                "id=" + id +
                ", role=" + role +
                ", permission=" + permission +
                '}';
    }
}
