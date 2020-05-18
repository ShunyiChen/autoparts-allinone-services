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
@Table(name = "slots")
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
    @OneToMany(mappedBy = "slot")
    private Set<SKUSlotMapping> SKUSlotMappings = new HashSet<>();
}
