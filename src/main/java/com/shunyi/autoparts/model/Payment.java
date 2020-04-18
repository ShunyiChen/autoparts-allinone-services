package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @description 结算方式实体类
 * @author Shunyi Chen
 * @date 2020/4/18
 */
@Entity
@Table(name = "payments")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Payment {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 结算方式编号 */
    private String code;
    /** 结算方式名称 */
    private String name;
    /** 备注 */
    private String notes;
}
