package com.shunyi.autoparts.exception;

/**
 * @description 产品找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String exception) {
        super(exception);
    }
}
