package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @description 门店实体类
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name = "stores")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Store {
    /** ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 店铺编码 */
    private String code;
    /** 名称 */
    private String name;
    /** 父类目ID */
    private Long parentId;
    /** 是否父节点 */
    private Boolean parent;
    /** 用户店铺映射关系 */
    @OneToMany(mappedBy = "store", fetch = FetchType.EAGER)
    protected Set<UserStoreMapping> userStoreMappingSet = new HashSet<>();
    /** 仓库 */
    @ManyToOne
    @JoinColumn(name = "store_warehouse_id",
            foreignKey = @ForeignKey(name = "STORE_WAREHOUSE_ID_FK")
    )
    private Warehouse warehouse;
}
