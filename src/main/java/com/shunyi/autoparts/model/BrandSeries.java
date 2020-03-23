package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @description 品牌
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name = "brand_series")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BrandSeries {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 类目 */
    @ManyToOne
    @JoinColumn(name = "brand_category_id",
            foreignKey = @ForeignKey(name = "BRAND_CATEGORY_ID_FK")
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
    private Date dateCreated;
    /** 创建者 */
    private String creator;
    /** 更新时间 */
    private Date dateUpdated;
    /** 更新者 */
    private String updater;
    /** 更新次数 */
    private Integer updatedCount;
    /** 删除时间 */
    private Date dateDeleted;
    /** 删除标记 */
    private Boolean deleteFlag;
    /** 删除者 */
    private String deleter;

    @Override
    public String toString() {
        return chineseName;
    }
}
