package com.shunyi.autoparts.model;

import javax.persistence.*;
import java.util.Date;

/** 产品属性 */
@Entity
@Table(name = "attributes")
public class Attribute {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 产品 */
    @ManyToOne
    @JoinColumn(name = "product_id",
            foreignKey = @ForeignKey(name = "PRODUCT_ID_FK")
    )
    private Product product;
    /** 属性名ID */
    private Long attributeNameId;
    /** 属性值ID */
    private Long attributeValueId;
    /** 是否SKU */
    private Boolean sku;
    /** SKU ID */
    private Long skuId;
    /** 创建时间 */
    private Date dateCreated;

    public Attribute() {}

    public Attribute(Product product, Long attributeNameId, Long attributeValueId, Boolean sku, Long skuId, Date dateCreated) {
        this.product = product;
        this.attributeNameId = attributeNameId;
        this.attributeValueId = attributeValueId;
        this.sku = sku;
        this.skuId = skuId;
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getAttributeNameId() {
        return attributeNameId;
    }

    public void setAttributeNameId(Long attributeNameId) {
        this.attributeNameId = attributeNameId;
    }

    public Long getAttributeValueId() {
        return attributeValueId;
    }

    public void setAttributeValueId(Long attributeValueId) {
        this.attributeValueId = attributeValueId;
    }

    public Boolean getSku() {
        return sku;
    }

    public void setSku(Boolean sku) {
        this.sku = sku;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
