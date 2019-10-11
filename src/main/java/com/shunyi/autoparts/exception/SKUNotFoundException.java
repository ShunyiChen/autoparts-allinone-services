package com.shunyi.autoparts.exception;

/** 产品SKU找不到异常 */
public class SKUNotFoundException extends RuntimeException {

    public SKUNotFoundException(String exception) {
        super(exception);
    }
}
