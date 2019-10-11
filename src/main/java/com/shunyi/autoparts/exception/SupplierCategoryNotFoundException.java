package com.shunyi.autoparts.exception;

/** 供应商类目找不到异常 */
public class SupplierCategoryNotFoundException extends RuntimeException {

    public SupplierCategoryNotFoundException(String exception) {
        super(exception);
    }
}
