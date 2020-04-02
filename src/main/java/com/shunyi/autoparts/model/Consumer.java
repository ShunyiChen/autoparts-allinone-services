package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @description 客户
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@Entity
@Table(name = "consumers")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Consumer {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 客户编码 */
    private String code;
    /** 客户单位名称 */
    private String name;
    /** 联系人 */
    private String contact;
    /** 电话好 */
    private String phone;
    /** Email */
    private String email;
    /** 车牌号 */
    private String licensePlate;
}
