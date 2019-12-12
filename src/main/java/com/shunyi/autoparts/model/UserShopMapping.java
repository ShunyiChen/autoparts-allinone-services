package com.shunyi.autoparts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/** 用户和店铺映射关系 */
@Entity
@Table(name="user_shop_mappings")
@org.hibernate.annotations.Immutable
public class UserShopMapping {

    @Embeddable
    public static class Id implements Serializable {

        /** 用户ID */
        @Column(name="user_id")
        protected Long userId;

        /** 店铺ID */
        @Column(name="shop_id")
        protected Long shopId;

        public Id() {}

        public Id(Long userId, Long shopId) {
            this.userId = userId;
            this.shopId = shopId;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getShopId() {
            return shopId;
        }

        public void setShopId(Long shopId) {
            this.shopId = shopId;
        }

        @Override
        public String toString() {
            return "Id{" +
                    "userId=" + userId +
                    ", shopId=" + shopId +
                    '}';
        }
    }

    @EmbeddedId
    protected UserShopMapping.Id id = new UserShopMapping.Id();

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
            name = "shop_id",
            insertable = false,
            updatable = false
    )
    @JsonIgnore
    protected Shop shop;

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

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
