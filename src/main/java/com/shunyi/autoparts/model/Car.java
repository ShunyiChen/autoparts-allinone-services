package com.shunyi.autoparts.model;

import javax.persistence.*;

/** 车型类目表 */
@Entity
@Table(name = "cars")
public class Car {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 车型编码 */
    private String code;
    /** 车型 */
    private String model;
    /** 父类目ID */
    private Long parentId;
    /** 是否父节点 */
    private Boolean parent;

    public Car() {}

    public Car(String code, String model, Long parentId, Boolean parent) {
        this.code = code;
        this.model = model;
        this.parentId = parentId;
        this.parent = parent;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Boolean getParent() {
        return parent;
    }

    public void setParent(Boolean parent) {
        this.parent = parent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", model='" + model + '\'' +
                ", parentId=" + parentId +
                ", parent=" + parent +
                '}';
    }
}
