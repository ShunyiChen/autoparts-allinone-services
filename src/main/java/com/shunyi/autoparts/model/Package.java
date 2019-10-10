package com.shunyi.autoparts.model;

import javax.persistence.*;
import java.util.Date;

/** 打包品表 */
@Entity
@Table(name = "packages")
public class Package {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 名称 */
    private String name;
    /** 状态 */
    private String status;
    /** 类型（组合SKU还是单个SKU） */
    private String type;
    /** 是否跨品牌 */
    private Boolean crossBrand;
    /** 打包品Key */
    private String packageKey;
    /** 创建时间 */
    private Date dateCreated;

    public Package() {}

    public Package(String name, String status, String type, Boolean crossBrand, String packageKey, Date dateCreated) {
        this.name = name;
        this.status = status;
        this.type = type;
        this.crossBrand = crossBrand;
        this.packageKey = packageKey;
        this.dateCreated = dateCreated;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getCrossBrand() {
        return crossBrand;
    }

    public void setCrossBrand(Boolean crossBrand) {
        this.crossBrand = crossBrand;
    }

    public String getPackageKey() {
        return packageKey;
    }

    public void setPackageKey(String packageKey) {
        this.packageKey = packageKey;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Package{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", crossBrand=" + crossBrand +
                ", packageKey='" + packageKey + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
