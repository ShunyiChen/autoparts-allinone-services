package com.shunyi.autoparts.model;

import javax.persistence.*;
import java.util.Date;

/** 产品基本属性 */
@Entity
@Table(name = "basic_attributes")
public class BasicAttribute {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /** 产品ID */
    private Long productId;

    /** 属性名ID */
    private Long attributeNameId;

    /** 属性值ID */
    private Long attributeValueId;

    /** 是否SKU */
    private Boolean isSKU;

    /** SKU ID */
    private Long skuId;

    /** 创建时间 */
    private Date dateCreated;

    public BasicAttribute() {}

    public BasicAttribute(Long productId, Long attributeNameId, Long attributeValueId, Boolean isSKU, Long skuId, Date dateCreated) {
        this.productId = productId;
        this.attributeNameId = attributeNameId;
        this.attributeValueId = attributeValueId;
        this.isSKU = isSKU;
        this.skuId = skuId;
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Boolean isSKU() {
        return isSKU;
    }

    public void setIsSKU(Boolean isSKU) {
        isSKU = isSKU;
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
