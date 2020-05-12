package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description 客户
 * @author Shunyi Chen
 * @date 2020/5/12
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
    /** 客户类别 */
    @ManyToOne
    @JoinColumn(name = "consumer_category_id",
            foreignKey = @ForeignKey(name = "CONSUMER_CATEGORY_ID_FK")
    )
    private ConsumerCategory consumerCategory;
    /** 客户编码 */
    private String code;
    /** 客户名称 */
    private String name;
    /** 联系人 */
    private String contact;
    /** 电话 */
    private String phone;
    /** 地址 */
    private String address;
    /** Email */
    private String email;
    /** 车牌号 */
    private String licensePlate;
    /** 当前欠款总额 */
    private BigDecimal amountOwing;
    /** 信用额度 */
    private BigDecimal lineOfCredit;
    /** 预收款总额 */
    private BigDecimal totalAmountReceived;
    /** 公司 */
    private String company;
    /** 备注 */
    private String notes;
}
