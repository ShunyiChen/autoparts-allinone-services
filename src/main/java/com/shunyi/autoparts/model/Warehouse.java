package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @description 仓库实体类
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name = "warehouses")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Warehouse {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 仓库编号 */
    private String code;
    /** 名称 */
    private String name;
    /** 父节点ID */
    private Long parentId;
    /** 是否是父节点 */
    private Boolean parent;
    /** 所属店铺 */
    @ManyToOne
    @JoinColumn(name = "warehouse_store_id",
            foreignKey = @ForeignKey(name = "WAREHOUSE_STORE_ID_FK")
    )
    private Store store;
}
