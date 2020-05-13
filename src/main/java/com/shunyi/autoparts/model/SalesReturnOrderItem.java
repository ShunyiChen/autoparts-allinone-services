package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Description: 销售退货单明细
 * @Author: Shunyi
 * @CreateDate: 2020/5/13
 */
@Entity
@Table(name = "sales_return_order_items")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class SalesReturnOrderItem {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 原销售单号 */
    private String originalOrderNo;
    /** 销售退货单 */
    @ManyToOne
    @JoinColumn(name = "sales_return_order_id",
            foreignKey = @ForeignKey(name = "SALES_RETURN_ORDER_ID_FK")
    )
    private SalesReturnOrder salesReturnOrder;
    /** SKU */
    @ManyToOne
    @JoinColumn(name = "sales_return_order_items_sku_id",
            foreignKey = @ForeignKey(name = "SALES_RETURN_ORDER_ITEMS_SKU_ID_FK")
    )
    private SKU sku;
    /** 数量 */
    private Integer quantity;
    /** 金额 */
    private BigDecimal amount;
    /** 折扣 */
    private BigDecimal discount;
    /** 折后金额 */
    private BigDecimal discountedAmount;
    /** 税率% */
    private BigDecimal taxRate;
    /** 税额 */
    private BigDecimal taxAmount;
    /** 含税单价 */
    private BigDecimal priceIncludingTax;
    /** 含税金额 */
    private BigDecimal amountIncludingTax;
    /** 不含税单价 */
    private BigDecimal priceExcludingTax;
    /** 不含税金额 */
    private BigDecimal amountExcludingTax;
    /** 备注 */
    private String notes;
}
