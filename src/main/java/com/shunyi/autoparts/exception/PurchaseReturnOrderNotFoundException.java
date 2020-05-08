package com.shunyi.autoparts.exception;

/**
 * @description 采购退货单找不到异常
 * @author Shunyi Chen
 * @date 2020/5/9
 */
public class PurchaseReturnOrderNotFoundException extends RuntimeException {

    public PurchaseReturnOrderNotFoundException(String exception) {
        super(exception);
    }
}
