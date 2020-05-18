package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Shunyi Chen
 * @description 进货单账号实体类
 * @date 2020/4/18
 */
@Entity
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** 编号 */
    private String code;
    /** 名称 */
    private String name;
    /** 备注 */
    private String notes;
}
