package com.shunyi.autoparts.exception;

/** 供应商找不到异常 */
public class SupplierNotFoundException extends RuntimeException {

    public SupplierNotFoundException(String exception) {
        super(exception);
    }
}
