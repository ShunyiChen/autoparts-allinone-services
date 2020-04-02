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
    /** 备注 */
    private String notes;
}
