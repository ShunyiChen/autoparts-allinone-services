package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description 销售退货单
 * @author Shunyi
 * @date 2020/5/13
 */
@Entity
@Table(name = "sales_return_orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class SalesReturnOrder {
    /** ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 单号 */
    private String orderNo;
    /** 单据日期 */
    private Date orderDate;
    /** 仓库 */
    @ManyToOne
    @JoinColumn(name = "sales_return_order_warehouse_id",
            foreignKey = @ForeignKey(name = "SALES_RETURN_ORDER_WAREHOUSE_ID_FK")
    )
    private Warehouse warehouse;
    /** 客户 */
    @ManyToOne
    @JoinColumn(name = "sales_return_consumer_id",
            foreignKey = @ForeignKey(name = "SALES_RETURN_ORDER_CONSUMER_ID_FK")
    )
    private Consumer consumer;
    /** 数量合计 */
    private Integer returnQty;
    /** 含税金额 */
    private BigDecimal amountIncludingTax;
    /** 不含税金额 */
    private BigDecimal amountExcludingTax;
    /** 税额 */
    private BigDecimal taxAmount;
    /** 总额 */
    private BigDecimal totalAmount;
    /** 代垫费用金额 */
    private BigDecimal disbursementAmount;
    /** 货款已退金额 */
    private BigDecimal returnedSalesAmount;
    /** 代垫费用已退金额 */
    private BigDecimal disbursementReturnedAmount;
    /** 已退款总额 */
    private BigDecimal totalReturnedAmount;
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
    /** 入库 */
    private Boolean warehousing;
    /** 搜索日期类型 */
    @Transient
    private String dateType;
    /** 起始日期 */
    @Transient
    private Date fromDate;
    /** 结束日期 */
    @Transient
    private Date toDate;
}
