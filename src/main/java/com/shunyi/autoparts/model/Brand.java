package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @description 配件品牌实体类
 * @author Shunyi Chen
 * @date 2020/4/2
 */
@Entity
@Table(name = "brands")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Brand {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 品牌编码 */
    private String code;
    /** 品牌名称 */
    private String name;
    /** 备注 */
    private String notes;
}
