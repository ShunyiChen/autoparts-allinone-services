package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @description 产品分类实体类
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Category {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 类目名称 */
    private String name;
    /** 父类目ID */
    private Long parentId;
    /** 是否父节点 */
    private Boolean parent;
    /** 店铺 */
    @ManyToOne
    @JoinColumn(name = "category_store_id",
            foreignKey = @ForeignKey(name = "CATEGORY_STORE_ID_FK")
    )
    private Store store;
}
