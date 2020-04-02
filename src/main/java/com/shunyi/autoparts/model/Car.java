package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @description 车型实体类
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name = "cars")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Car {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 车型编码 */
    private String code;
    /** 车型名称 */
    private String name;
    /** 备注 */
    private String notes;
}
