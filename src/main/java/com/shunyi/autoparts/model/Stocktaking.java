package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @Description: 盘点方式实体类
 * @Author: Shunyi
 * @CreateDate: 2020/5/14
 */
@Entity
@Table(name = "stocktaking")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Stocktaking {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 盘点方式编码 */
    private String code;
    /** 盘点方式名称 */
    private String name;
    /** 备注 */
    private String notes;
}
