package com.shunyi.autoparts.exception;

/**
 * @description 采购订单找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class PurchaseOrderNotFoundException extends RuntimeException {

    public PurchaseOrderNotFoundException(String exception) {
        super(exception);
    }
}
