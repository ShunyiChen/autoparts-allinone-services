package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @description 产品属性名
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name = "attribute_names")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttributeName {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 属性名 */
    private String name;
    /** 类目 */
    @ManyToOne
    @JoinColumn(name = "name_category_id",
        foreignKey = @ForeignKey(name = "NAME_CATEGORY_ID_FK")
    )
    private Category category;
    /** 是否允许别名 */
    private Boolean aliasAllowed;
    /** 是否颜色属性 */
    private Boolean colorProperty;
    /** 是否枚举属性 */
    private Boolean enumProperty;
    /** 是否输入属性 */
    private Boolean inputProperty;
    /** 是否关键属性 */
    private Boolean keyProperty;
    /** 是否销售属性 */
    private Boolean saleProperty;
    /** 是否搜索字段 */
    private Boolean searchField;
    /** 是否必须 */
    private Boolean required;
    /** 是否多选 */
    private Boolean multiple;
    /** 状态 */
    private String status;
    /** 排序 */
    private Integer sort;
    @Override
    public String toString() {
        return name;
    }
}
