package com.shunyi.autoparts.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/** 进货单实体类 */
@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /** 单号 */
    private String orderId;
    /** 单据日期 */
    private Date orderDate;
    /** 仓库 */
    @ManyToOne
    @JoinColumn(name = "order_warehouse_id",
            foreignKey = @ForeignKey(name = "ORDER_WAREHOUSE_ID_FK")
    )
    private Warehouse warehouse;
    /** 打包及其明细 */
    @OneToOne
    @JoinColumn(name = "package_id",
            foreignKey = @ForeignKey(name = "PACKAGE_ID_FK")
    )
    private Package aPackage;
    /** 供应商 */
    @ManyToOne
    @JoinColumn(name = "supplier_id",
            foreignKey = @ForeignKey(name = "SUPPLIER_ID_FK")
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
    /** 操作用户 */
    @ManyToOne
    @JoinColumn(name = "operator_id",
            foreignKey = @ForeignKey(name = "OPERATOR_ID_FK")
    )
    private User operator;
    /** 经办人 */
    @ManyToOne
    @JoinColumn(name = "verifier_id",
            foreignKey = @ForeignKey(name = "VERIFIER_ID_FK")
    )
    private User verifier;
    /** 结算方式 */
    @OneToOne
    @JoinColumn(name = "settlement_id")
    private Settlement settlement;
    /** 货款金额 */
    private BigDecimal amountA;
    /** 代垫费用 */
    private BigDecimal amountB;
    /** 本次优惠 */
    private BigDecimal amountC;
    /** 应付总额 */
    private BigDecimal amountD;
    /** 本次付款 */
    private BigDecimal amountE;
    /** 账号 */
    private String account;
    /** 订单状态 */
    private String status;

    public PurchaseOrder() {}

    public PurchaseOrder(String orderId, Date orderDate, Warehouse warehouse, Supplier supplier, String invoiceType, String invoiceNo, BigDecimal freight, String notes, User operator, User verifier, Settlement settlement, BigDecimal amountA, BigDecimal amountB, BigDecimal amountC, BigDecimal amountD, BigDecimal amountE, String account, String status) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.warehouse = warehouse;
        this.supplier = supplier;
        this.invoiceType = invoiceType;
        this.invoiceNo = invoiceNo;
        this.freight = freight;
        this.notes = notes;
        this.operator = operator;
        this.verifier = verifier;
        this.settlement = settlement;
        this.amountA = amountA;
        this.amountB = amountB;
        this.amountC = amountC;
        this.amountD = amountD;
        this.amountE = amountE;
        this.account = account;
        this.status = status;
    }

    public Package getPackage() {
        return aPackage;
    }

    public void setPackage(Package aPackage) {
        this.aPackage = aPackage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public User getOperator() {
        return operator;
    }

    public void setOperator(User operator) {
        this.operator = operator;
    }

    public User getVerifier() {
        return verifier;
    }

    public void setVerifier(User verifier) {
        this.verifier = verifier;
    }

    public Settlement getSettlement() {
        return settlement;
    }

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }

    public BigDecimal getAmountA() {
        return amountA;
    }

    public void setAmountA(BigDecimal amountA) {
        this.amountA = amountA;
    }

    public BigDecimal getAmountB() {
        return amountB;
    }

    public void setAmountB(BigDecimal amountB) {
        this.amountB = amountB;
    }

    public BigDecimal getAmountC() {
        return amountC;
    }

    public void setAmountC(BigDecimal amountC) {
        this.amountC = amountC;
    }

    public BigDecimal getAmountD() {
        return amountD;
    }

    public void setAmountD(BigDecimal amountD) {
        this.amountD = amountD;
    }

    public BigDecimal getAmountE() {
        return amountE;
    }

    public void setAmountE(BigDecimal amountE) {
        this.amountE = amountE;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Purchaseorder{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", warehouse=" + warehouse +
                ", supplier=" + supplier +
                ", invoiceType='" + invoiceType + '\'' +
                ", invoiceNo='" + invoiceNo + '\'' +
                ", freight=" + freight +
                ", notes='" + notes + '\'' +
                ", operator=" + operator +
                ", verifier=" + verifier +
                ", settlement=" + settlement +
                ", amountA=" + amountA +
                ", amountB=" + amountB +
                ", amountC=" + amountC +
                ", amountD=" + amountD +
                ", amountE=" + amountE +
                ", account='" + account + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
