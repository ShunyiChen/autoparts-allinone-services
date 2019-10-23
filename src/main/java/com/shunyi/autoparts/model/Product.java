package com.shunyi.autoparts.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/** 产品（汽车配件） */
@Entity
@Table(name = "products")
public class Product {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 产品编码 */
    private String code;
    /** 产品名称 */
    private String name;
    /** 品牌ID */
    private Long brandSeriesId;
    /** 不含税单价 */
    private BigDecimal priceExcludingTax;
    /** 创建时间 */
    private Date dateCreated;

    public Product() {}

    public Product(String code, String name, Long brandSeriesId, BigDecimal priceExcludingTax, Date dateCreated) {
        this.code = code;
        this.name = name;
        this.brandSeriesId = brandSeriesId;
        this.priceExcludingTax = priceExcludingTax;
        this.dateCreated = dateCreated;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBrandSeriesId() {
        return brandSeriesId;
    }

    public void setBrandSeriesId(Long brandSeriesId) {
        this.brandSeriesId = brandSeriesId;
    }

    public BigDecimal getPriceExcludingTax() {
        return priceExcludingTax;
    }

    public void setPriceExcludingTax(BigDecimal priceExcludingTax) {
        this.priceExcludingTax = priceExcludingTax;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", brandSeriesId=" + brandSeriesId +
                ", priceExcludingTax=" + priceExcludingTax +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
