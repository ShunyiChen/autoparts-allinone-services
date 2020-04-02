package com.shunyi.autoparts.exception;

/**
 * @description 品牌找不到异常
 * @author Shunyi Chen
 * @date 2020/4/2
 */
public class BrandNotFoundException extends RuntimeException {
    public BrandNotFoundException(String exception) {
        super(exception);
    }
}
