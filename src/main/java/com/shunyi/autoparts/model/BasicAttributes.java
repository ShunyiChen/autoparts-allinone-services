package com.shunyi.autoparts.model;

import javax.persistence.*;
import java.util.Date;

/** 产品基本属性 */
@Entity
@Table(name = "basic_attributes")
public class BasicAttributes {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /** 产品ID */
    @ManyToOne
    @JoinColumn(name = "basic_attribute_product_id",
        foreignKey = @ForeignKey(name = "BASIC_ATTRIBUTE_PRODUCT_ID_FK")
    )
    private Product product;

    /** 基本属性名ID */
    private Long attributeNameId;

    /** 基本属性值ID */
    @ManyToOne
    @JoinColumn(name = "basic_attribute_value_id",
              foreignKey = @ForeignKey(name = "BASIC_ATTRIBUTE_VALUE_ID_FK")
    )
    private AttributeValue attributeValue;

    /** 是否SKU */
    private Boolean isSKU;

    /** SKU ID */
    private Long skuId;

    /** 创建时间 */
    private Date dateCreated;

    public BasicAttributes() {}

    public BasicAttributes(Product product, Long attributeNameId, AttributeValue attributeValue, Boolean isSKU, Long skuId, Date dateCreated) {
        this.product = product;
        this.attributeNameId = attributeNameId;
        this.attributeValue = attributeValue;
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

    public AttributeValue getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(AttributeValue attributeValue) {
        this.attributeValue = attributeValue;
    }

    public Boolean getIsSKU() {
        return isSKU;
    }

    public void setIsSKU(Boolean SKU) {
        isSKU = SKU;
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
