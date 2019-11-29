package com.shunyi.autoparts.model;

import javax.persistence.*;
import java.util.Date;

/** 产品属性值表 */
@Entity
@Table(name = "attribute_values")
public class AttributeValue {
    /** 自增ID*/
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 属性值名称 */
    private String name;
    /** 颜色RGB */
    private String rgb;
    /** 类目 */
    @ManyToOne
    @JoinColumn(name = "value_category_id",
            foreignKey = @ForeignKey(name = "VALUE_CATEGORY_ID_FK")
    )
    private Category category;
    /** 属性名 */
    @ManyToOne
    @JoinColumn(name = "attributeName_id",
            foreignKey = @ForeignKey(name = "ATTRIBUTENAME_ID_FK")
    )
    private AttributeName attributeName;
    /** 状态 */
    private String status;
    /** 排序 */
    private Integer sort;
    /** 创建时间 */
    private Date dateCreated;

    public AttributeValue() {}

    public AttributeValue(String name, String rgb, Category category, AttributeName attributeName, String status, Integer sort, Date dateCreated) {
        this.name = name;
        this.rgb = rgb;
        this.category = category;
        this.attributeName = attributeName;
        this.status = status;
        this.sort = sort;
        this.dateCreated = dateCreated;
    }

    public String getRgb() {
        return rgb;
    }

    public void setRgb(String rgb) {
        this.rgb = rgb;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public AttributeName getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(AttributeName attributeName) {
        this.attributeName = attributeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return name;
    }
}
