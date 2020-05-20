package com.shunyi.autoparts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Description: 采购退货单明细
 * @Author: 陈顺谊
 * @CreateDate: 2020/5/9
 */
@Entity
@Table(name = "purchase_return_order_items")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PurchaseReturnOrderItem {
    /** 自增ID */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 原进货单号 */
    private String originalOrderNo;
    /** 采购退货单 */
    @ManyToOne
    @JoinColumn(name = "purchase_return_order_id",
            foreignKey = @ForeignKey(name = "PURCHASE_RETURN_ORDER_ID_FK")
    )
    private PurchaseReturnOrder purchaseReturnOrder;
    /** SKU */
    @ManyToOne
    @JoinColumn(name = "purchase_return_order_items_sku_id",
            foreignKey = @ForeignKey(name = "PURCHASE_RETURN_ORDER_ITEMS_SKU_ID_FK")
    )
    private SKU sku;
    /** 数量 */
    private Integer quantity;
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
    /** 可退货数量 */
    private Integer returnableQty;
}
