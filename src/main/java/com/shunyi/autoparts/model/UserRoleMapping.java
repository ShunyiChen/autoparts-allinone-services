package com.shunyi.autoparts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/** 用户和角色映射关系 */
@Entity
@Table(name="user_role_mappings")
@org.hibernate.annotations.Immutable
public class UserRoleMapping {
    @Embeddable
    public static class Id implements Serializable {
        @Column(name="userId")
        protected Long userId;
        @Column(name="roleId")
        protected Long roleId;

        public Id() {}

        /**
         * Constructor
         *
         * @param userId 用户ID
         * @param roleId 角色ID
         */
        public Id(Long userId, Long roleId) {
            this.userId = userId;
            this.roleId = roleId;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getRoleId() {
            return roleId;
        }

        public void setRoleId(Long roleId) {
            this.roleId = roleId;
        }
    }

    @EmbeddedId
    protected Id id = new Id();

    @ManyToOne
    @JoinColumn(
            name = "roleId",
            insertable = false, updatable = false
    )
    @JsonIgnore
    protected Role role;

    @ManyToOne
    @JoinColumn(
            name = "userId",
            insertable = false, updatable = false
    )
    @JsonIgnore
    protected User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserRoleMapping{" +
                "id=" + id +
                ", role=" + role +
                ", user=" + user +
                '}';
    }
}
