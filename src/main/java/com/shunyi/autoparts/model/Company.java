package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @description 所属公司实体类
 * @author Shunyi Chen
 * @date 2020/4/13
 */
@Entity
@Table(name = "companies")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Company {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 公司编号 */
    private String code;
    /** 公司名称 */
    private String name;
    /** 备注 */
    private String notes;
}
