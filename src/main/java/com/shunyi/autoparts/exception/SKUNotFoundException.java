package com.shunyi.autoparts.exception;

/**
 * @description 产品SKU找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class SKUNotFoundException extends RuntimeException {

    public SKUNotFoundException(String exception) {
        super(exception);
    }
}
