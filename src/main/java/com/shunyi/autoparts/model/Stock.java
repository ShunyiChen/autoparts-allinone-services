package com.shunyi.autoparts.model;

import javax.persistence.*;

/** 产品库存 */
@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 仓库ID */
    private Long warehouseId;
    /** 产品ID */
    private Long productId;
    /** SKU ID */
    private Long skuId;

    public Stock() {}

    public Stock(Long warehouseId, Long productId, Long skuId) {
        this.warehouseId = warehouseId;
        this.productId = productId;
        this.skuId = skuId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Long getproductId() {
        return productId;
    }

    public void setproductId(Long productId) {
        this.productId = productId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", warehouseId=" + warehouseId +
                ", productId=" + productId +
                ", skuId=" + skuId +
                '}';
    }
}
