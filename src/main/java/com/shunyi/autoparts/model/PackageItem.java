package com.shunyi.autoparts.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/** 打包品余项表 */
@Entity
@Table(name = "package_items")
public class PackageItem {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 打包品ID */
    private Long packageId;
    /** SKU ID */
    private Long skuId;
    /** 数量 */
    private Integer quantity;
    /** 总价 */
    private BigDecimal totalPrice;
    /** 创建时间 */
    private Date dateCreated;

    public PackageItem() {}

    public PackageItem(Long packageId, Long skuId, Integer quantity, BigDecimal totalPrice, Date dateCreated) {
        this.packageId = packageId;
        this.skuId = skuId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "PackageItem{" +
                "id=" + id +
                ", packageId=" + packageId +
                ", skuId=" + skuId +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
