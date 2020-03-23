package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @description 产品基本属性
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name = "basic_attributes")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BasicAttributes {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 产品 */
    @ManyToOne
    @JoinColumn(name = "basic_attribute_product_id",
        foreignKey = @ForeignKey(name = "BASIC_ATTRIBUTE_PRODUCT_ID_FK")
    )
    private Product product;
    /** 基本属性名ID */
    private Long attributeNameId;
    /** 基本属性值ID */
    @ManyToOne
    @JoinColumn(name = "basic_attribute_value_id",
              foreignKey = @ForeignKey(name = "BASIC_ATTRIBUTE_VALUE_ID_FK")
    )
    private AttributeValue attributeValue;
    /** 是否SKU */
    private Boolean isSKU;
    /** SKU ID */
    private Long skuId;
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
