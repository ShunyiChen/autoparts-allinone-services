package com.shunyi.autoparts.exception;

/**
 * @description 采购订单明细找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class PurchaseOrderItemNotFoundException extends RuntimeException {

    public PurchaseOrderItemNotFoundException(String exception) {
        super(exception);
    }
}
