package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @description 发票类型实体类
 * @author Shunyi Chen
 * @date 2020/4/18
 */
@Entity
@Table(name = "invoice_types")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class InvoiceType {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 发票类型编号 */
    private String code;
    /** 发票类型名称 */
    private String name;
    /** 备注 */
    private String notes;
}
