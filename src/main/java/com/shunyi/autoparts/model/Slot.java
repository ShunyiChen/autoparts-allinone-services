package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @description 货位
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name = "cargo_spaces")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Slot {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 仓库（仓库编号） */
    @ManyToOne
    @JoinColumn(name = "slot_warehouse_id",
            foreignKey = @ForeignKey(name = "SLOT_WAREHOUSE_ID_FK")
    )
    private Warehouse warehouse;
    /** 货位名称 */
    private String name;
    /** 条形码 */
    private String barCode;
    /** 第几区货架/库区编号 */
    private String level_1;
    /** 通道编号 */
    private String level_2;
    /** 第几个货架/货架组编号 */
    private String level_3;
    /** 第几层货架/货架层号 */
    private String level_4;
    /** 第几个存放盒位/货架层中库位 */
    private String level_5;
    /** SKU与货位映射集合 */
    @OneToMany(mappedBy = "cargoSpace")
    private Set<SKUCargoSpaceMapping> SKUCargoSpaceMappings = new HashSet<>();
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
