package com.shunyi.autoparts.exception;

/**
 * @description 采购退货单明细找不到异常
 * @author Shunyi Chen
 * @date 2020/5/9
 */
public class PurchaseReturnOrderItemNotFoundException extends RuntimeException {

    public PurchaseReturnOrderItemNotFoundException(String exception) {
        super(exception);
    }
}
