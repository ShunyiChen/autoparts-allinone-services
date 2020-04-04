package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @description 产品SKU实体类
 * @author Shunyi Chen
 * @date 2020/4/4
 */
@Entity
@Table(name = "sku")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class SKU {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 产品SPU */
    @ManyToOne
    @JoinColumn(name = "sku_product_id",
            foreignKey = @ForeignKey(name = "SKU_PRODUCT_ID_FK")
    )
    private Product product;
    /** SKU编码 */
    private String skuCode;
    /** SKU名称 */
    private String skuName;
    /** 规格 */
    private String specification;
    /** 条形码 */
    private String skuBarCode;
    /** 库存数量 */
    private Integer stockQty;
    /** 属性字符串 */
    private String properties;
    /** 折扣% */
    private String discountPercentage;
    /** 可用状态 */
    private String status;
    /** 备注 */
    private String notes;
    /** 进货平均价 */
    private BigDecimal avgPrice;
    /** SKU与货位映射集合 */
    @OneToMany(mappedBy = "sku")
    private Set<SKUSlotMapping> skuSlotMappingSet = new HashSet<>();
    /** SKU图片列表 */
    @OneToMany
    @JoinColumn(name = "sku_photo_id",
            foreignKey = @ForeignKey(name = "SKU_PHOTO_ID_FK")
    )
    private Set<SKUPhoto> photos = new HashSet<>();
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
}
