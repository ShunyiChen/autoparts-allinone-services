package com.shunyi.autoparts.exception;

/**
 * @description 供应商找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class SupplierNotFoundException extends RuntimeException {

    public SupplierNotFoundException(String exception) {
        super(exception);
    }
}
