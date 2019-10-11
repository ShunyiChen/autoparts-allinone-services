package com.shunyi.autoparts.exception;

/** 进货单找不到异常 */
public class PurchaseOrderNotFoundException extends RuntimeException {

    public PurchaseOrderNotFoundException(String exception) {
        super(exception);
    }
}
