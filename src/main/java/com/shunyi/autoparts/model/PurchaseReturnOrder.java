package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description 采购退货单
 * @author Shunyi Chen
 * @date 2020/5/9
 */
@Entity
@Table(name = "purchase_return_orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PurchaseReturnOrder {
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
    @JoinColumn(name = "purchase_return_order_warehouse_id",
            foreignKey = @ForeignKey(name = "PURCHASE_RETURN_ORDER_WAREHOUSE_ID_FK")
    )
    private Warehouse warehouse;
    /** 供应商 */
    @ManyToOne
    @JoinColumn(name = "purchase_return_order_supplier_id",
            foreignKey = @ForeignKey(name = "PURCHASE_RETURN_ORDER_SUPPLIER_ID_FK")
    )
    private Supplier supplier;
    /** 退回数量 */
    private Integer returnQty;
    /** 未税金额 */
    private BigDecimal totalAmountExcludingTax;
    /** 含税金额 */
    private BigDecimal totalAmountIncludingTax;
    /** 税额 */
    private BigDecimal taxAmount;
    /** 费用金额 */
    private BigDecimal totalFees;
    /** 费用已付金额 */
    private BigDecimal totalFeesPaid;
    /** 货款已付金额 */
    private BigDecimal goodsPaymentPaid;
    /** 应付总额 */
    private BigDecimal amountPayable;
    /** 付款金额合计 */
    private BigDecimal totalPaymentAmount;
    /** 发票类型 */
    private String invoiceType;
    /** 发票号 */
    private String invoiceNo;
    /** 备注 */
    private String notes;
    /** 经办人 */
    private String operator;
    /** 操作员 */
    private String userName;
    /** 结算方式 */
    private String payment;
    /** 订单状态 */
    private String status;
    /** 创建时间 */
    private Date dateCreated;
    /** 创建者 */
    private String creator;
    /** 出库 */
    private Boolean warehousingOut;
    /** 起始日期 */
    @Transient
    private Date fromDate;
    /** 结束日期 */
    @Transient
    private Date toDate;
}
