package com.shunyi.autoparts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @description 用户和角色映射关系
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name="user_role_mappings")
@org.hibernate.annotations.Immutable
public class UserRoleMapping {
    @Embeddable
    public static class Id implements Serializable {

        /** 用户ID */
        @Column(name="user_id")
        protected Long userId;

        /** 角色ID */
        @Column(name="role_id")
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

        @Override
        public boolean equals(Object o) {
            if (this == o) { return true;}
            if (o == null || getClass() != o.getClass()) {return false;}
            Id id = (Id) o;
            return Objects.equals(userId, id.userId) &&
                    Objects.equals(roleId, id.roleId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, roleId);
        }

        @Override
        public String toString() {
            return "Id{" +
                    "userId=" + userId +
                    ", roleId=" + roleId +
                    '}';
        }
    }

    @EmbeddedId
    protected Id id = new Id();

    @ManyToOne
    @JoinColumn(
            name = "role_id",
            foreignKey = @ForeignKey(name = "URM_ROLE_ID_FK"),
            insertable = false, updatable = false
    )
    @JsonIgnore
    protected Role role;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(name = "URM_USER_ID_FK"),
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
