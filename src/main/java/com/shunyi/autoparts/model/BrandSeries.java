package com.shunyi.autoparts.model;

import javax.persistence.*;
import java.util.Date;

/** 品牌系列表 */
@Entity
@Table(name = "brand_series")
public class BrandSeries {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 类目 */
    @ManyToOne
    @JoinColumn(name = "category_id",
            foreignKey = @ForeignKey(name = "CATEGORY_ID_FK")
    )
    private Category category;
    /** 品牌中文名 */
    private String chineseName;
    /** 品牌英文名 */
    private String englishName;
    /** 描述 */
    private String description;
    /** 品牌Logo */
    @OneToOne
    @JoinColumn(name = "logo_id",
            foreignKey = @ForeignKey(name = "LOGO_ID_FK")
    )
    private Logo logo;
    /** 状态 */
    private String status;
    /** 品牌官方网站 */
    private String officialSite;
    /** 创建时间 */
//    //入参哥格式化
//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    //出参格式化
//    @JsonFormat(
//        pattern = "yyyy-MM-dd HH:mm:ss"
//    )
    private Date dateCreated;

    public BrandSeries() {
    }

    public BrandSeries(Category category, String chineseName, String englishName, String description, Logo logo, String status, String officialSite, Date dateCreated) {
        this.category = category;
        this.chineseName = chineseName;
        this.englishName = englishName;
        this.description = description;
        this.logo = logo;
        this.status = status;
        this.officialSite = officialSite;
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOfficialSite() {
        return officialSite;
    }

    public void setOfficialSite(String officialSite) {
        this.officialSite = officialSite;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "BrandSeries{" +
                "id=" + id +
                ", category=" + category +
                ", chineseName='" + chineseName + '\'' +
                ", englishName='" + englishName + '\'' +
                ", description='" + description + '\'' +
                ", logo=" + logo +
                ", status='" + status + '\'' +
                ", officialSite='" + officialSite + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
