package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @Description: 发货方式
 * @Author: Shunyi
 * @CreateDate: 2020/5/11 23:09
 */
@Entity
@Table(name = "deliveries")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Delivery {
    /** ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 发货方式名称 */
    private String name;
    /** 货运编码 */
    private String freightCode;
}
