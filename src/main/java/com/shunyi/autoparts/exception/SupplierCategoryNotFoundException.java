package com.shunyi.autoparts.exception;

/**
 * @description 供应商类目找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class SupplierCategoryNotFoundException extends RuntimeException {

    public SupplierCategoryNotFoundException(String exception) {
        super(exception);
    }
}
