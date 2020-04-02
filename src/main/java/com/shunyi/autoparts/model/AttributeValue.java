package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @description 产品属性值
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name = "attribute_values")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttributeValue {
    /** 自增ID*/
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 属性值名称 */
    private String name;
    /** 颜色RGB */
    private String rgb;
    /** 类目 */
    @ManyToOne
    @JoinColumn(name = "value_category_id",
            foreignKey = @ForeignKey(name = "VALUE_CATEGORY_ID_FK")
    )
    private Category category;
    /** 属性名 */
    @ManyToOne
    @JoinColumn(name = "attributeName_id",
            foreignKey = @ForeignKey(name = "ATTRIBUTENAME_ID_FK")
    )
    private AttributeName attributeName;
    /** 状态 */
    private String status;
    /** 排序 */
    private Integer sort;
    @Override
    public String toString() {
        return name;
    }
}
