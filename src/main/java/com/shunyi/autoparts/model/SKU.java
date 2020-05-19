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
 * @author Shunyi
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
    /** 库存平均价 */
    private Integer stockAvgPrice;
    /** 库存金额 */
    private Integer stockAmount;
    /** 进货平均单价 */
    private BigDecimal purchaseAvgPrice;
    /** 进货金额 */
    private BigDecimal purchaseAmount;
    /** 属性字符串 */
    private String properties;
    /** 折扣% */
    private BigDecimal discount;
    /** 可用状态 */
    private Boolean enabled;
    /** 备注 */
    private String notes;
    /** SKU与货位映射集合 */
    @OneToMany(mappedBy = "sku")
    private Set<SKUSlotMapping> skuSlotMappingSet = new HashSet<>();
    /** SKU图片列表 */
    @OneToMany
    @JoinColumn(name = "sku_photo_id",
            foreignKey = @ForeignKey(name = "SKU_PHOTO_ID_FK")
    )
    private Set<SKUPhoto> photos = new HashSet<>();
}
