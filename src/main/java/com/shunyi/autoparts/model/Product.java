package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description 产品（汽车配件）
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Product {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 产品编码 */
    private String code;
    /** 条形码 */
    private String barCode;
    /** 产品名称 */
    private String name;
    /** 品牌 */
    @ManyToOne
    @JoinColumn(name = "brandSeries_id",
            foreignKey = @ForeignKey(name = "BRANDSERIES_ID_FK")
    )
    private BrandSeries brandSeries;
    /** 通用车型 */
    @ManyToOne
    @JoinColumn(name = "car_id",
            foreignKey = @ForeignKey(name = "CAR_ID_FK")
    )
    private Car car;
    /** 供应商 */
    @ManyToOne
    @JoinColumn(name = "supplier_id",
            foreignKey = @ForeignKey(name = "SUPPLIER_ID_FK")
    )
    private Supplier supplier;
    /** 单位 */
    private String unit;
    /** 列表价 */
    private BigDecimal listPrice;
    /** 进口 */
    private String imported;
    /** 原产地 */
    private String origin;
    /** 其他 */
    private String notes;
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
