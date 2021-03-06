package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Description: 采购订单明细
 * @Author: 陈顺谊
 * @CreateDate: 2020/4/1 12:36
 * @Version: 1.0
 */
@Entity
@Table(name = "purchase_order_items")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PurchaseOrderItem {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 采购订单 */
    @ManyToOne
    @JoinColumn(name = "purchase_order_id",
            foreignKey = @ForeignKey(name = "PURCHASE_ORDER_ID_FK")
    )
    private PurchaseOrder purchaseOrder;
    /** SKU */
    @ManyToOne
    @JoinColumn(name = "purchase_order_items_sku_id",
            foreignKey = @ForeignKey(name = "PURCHASE_ORDER_ITEMS_SKU_ID_FK")
    )
    private SKU sku;
    /** 金额 */
    private BigDecimal amount;
    /** 折扣 */
    private BigDecimal discount;
    /** 折后金额 */
    private BigDecimal discountedAmount;
    /** 税率% */
    private BigDecimal taxRate;
    /** 税率% */
    private BigDecimal taxAmount;
    /** 含税单价 */
    private BigDecimal priceIncludingTax;
    /** 含税金额 */
    private BigDecimal amountIncludingTax;
    /** 备注 */
    private String notes;
}
