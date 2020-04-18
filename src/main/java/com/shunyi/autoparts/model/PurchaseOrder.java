package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description 采购订单
 * @author Shunyi Chen
 * @date 2020/4/4
 */
@Entity
@Table(name = "purchase_orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PurchaseOrder {
    /** ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 业务单号 */
    private String orderNo;
    /** 单据日期 */
    private Date orderDate;
    /** 仓库 */
    @ManyToOne
    @JoinColumn(name = "purchase_warehouse_id",
            foreignKey = @ForeignKey(name = "PURCHASE_WAREHOUSE_ID_FK")
    )
    private Warehouse warehouse;
    /** 供应商 */
    @ManyToOne
    @JoinColumn(name = "purchase_supplier_id",
            foreignKey = @ForeignKey(name = "PURCHASE_SUPPLIER_ID_FK")
    )
    private Supplier supplier;
    /** 发票类型 */
    private String invoiceType;
    /** 发票号 */
    private String invoiceNo;
    /** 运费 */
    private BigDecimal freight;
    /** 备注 */
    private String notes;
    /** 经办人 */
    private String operator;
    /** 结算方式 */
    private String payment;
    /** 货款金额 */
    private BigDecimal purchaseAmount;
    /** 垫付费用 */
    private BigDecimal disbursement;
    /** 本次优惠 */
    private BigDecimal discountAmount;
    /** 应付总额 */
    private BigDecimal amountPayable;
    /** 本次付款 */
    private BigDecimal paymentAmount;
    /** 账号 */
    private String account;
    /** 还款日期 */
    private Date repaymentDate;
    /** 订单状态 */
    private String status;
    /** 创建时间 */
    private Date dateCreated;
    /** 创建者 */
    private String creator;
    /** 删除时间 */
    private Date dateDeleted;
    /** 删除标记 */
    private Boolean deleteFlag;
    /** 删除者 */
    private String deleter;
}
