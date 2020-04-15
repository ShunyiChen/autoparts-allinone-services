package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description 配件实体类
 * @author Shunyi Chen
 * @date 2020/4/13
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
    /** 类别 */
    @ManyToOne
    @JoinColumn(name = "product_category_id",
            foreignKey = @ForeignKey(name = "PRODUCT_CATEGORY_ID_FK")
    )
    private Category category;
    /** 产品名称 */
    private String name;
    /** 单位 */
    @ManyToOne
    @JoinColumn(name = "unit_id",
            foreignKey = @ForeignKey(name = "UNIT_ID_FK")
    )
    private Unit unit;
    /** 条形码 */
    private String barCode;
    /** 车型 */
    @ManyToOne
    @JoinColumn(name = "car_id",
            foreignKey = @ForeignKey(name = "CAR_ID_FK")
    )
    private Car car;
    /** 通用车型 */
    private String relevantModels;
    /** 产地 */
    @ManyToOne
    @JoinColumn(name = "place_id",
            foreignKey = @ForeignKey(name = "PLACE_ID_FK")
    )
    private Place place;
    /** 品牌 */
    @ManyToOne
    @JoinColumn(name = "brand_id",
            foreignKey = @ForeignKey(name = "BRAND_ID_FK")
    )
    private Brand brand;
    /** 英文名 */
    private String englishName;
    /** 进口 */
    @ManyToOne
    @JoinColumn(name = "imported_id",
            foreignKey = @ForeignKey(name = "IMPORTED_ID_FK")
    )
    private Import imported;
    /** 通用件号 */
    private String commonNumber;
    /** 材料 */
    private String materials;
    /** 所属公司 */
    @ManyToOne
    @JoinColumn(name = "company_id",
            foreignKey = @ForeignKey(name = "COMPANY_ID_FK")
    )
    private Company company;
    /** 包装数量 */
    private Integer packingQuantity;
    /** 重量 */
    private String weight;
    /** 手册名称 */
    private String manual;
    /** 供应商 */
    @ManyToOne
    @JoinColumn(name = "supplier_id",
            foreignKey = @ForeignKey(name = "SUPPLIER_ID_FK")
    )
    private Supplier supplier;
    /** 一级进价 */
    private BigDecimal  purchasingPrice1;
    /** 二级进价 */
    private BigDecimal  purchasingPrice2;
    /** 三级进价 */
    private BigDecimal  purchasingPrice3;
    /** 一级销价 */
    private BigDecimal  sellingPrice1;
    /** 二级销价 */
    private BigDecimal  sellingPrice2;
    /** 三级销价 */
    private BigDecimal  sellingPrice3;
    /** 最低销价 */
    private BigDecimal  bottomPrice;
    /** 外币单位 */
    private String  foreignCurrencyUnit;
    /** 外币价格 */
    private String  foreignCurrencyPrice;
    /** 紧缺件 */
    private Boolean shortage;
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
