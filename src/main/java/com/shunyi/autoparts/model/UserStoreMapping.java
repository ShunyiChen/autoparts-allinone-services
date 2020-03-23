package com.shunyi.autoparts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @description 用户和店铺映射关系
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name="user_store_mappings")
@org.hibernate.annotations.Immutable
public class UserStoreMapping {

    @Embeddable
    public static class Id implements Serializable {

        /** 用户ID */
        @Column(name="user_id")
        protected Long userId;
        /** 店铺ID */
        @Column(name="store_id")
        protected Long storeId;

        public Id() {}

        public Id(Long userId, Long storeId) {
            this.userId = userId;
            this.storeId = storeId;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getStoreId() {
            return storeId;
        }

        public void setStoreId(Long storeId) {
            this.storeId = storeId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Id id = (Id) o;
            return Objects.equals(userId, id.userId) &&
                    Objects.equals(storeId, id.storeId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, storeId);
        }

        @Override
        public String toString() {
            return "Id{" +
                    "userId=" + userId +
                    ", storeId=" + storeId +
                    '}';
        }
    }

    @EmbeddedId
    protected UserStoreMapping.Id id = new UserStoreMapping.Id();

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            insertable = false,
            updatable = false
    )
    @JsonIgnore
    protected User user;

    @ManyToOne
    @JoinColumn(
            name = "store_id",
            insertable = false,
            updatable = false
    )
    @JsonIgnore
    protected Store store;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
