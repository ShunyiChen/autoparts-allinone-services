package com.shunyi.autoparts.exception;

/** 产品找不到异常 */
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String exception) {
        super(exception);
    }
}
