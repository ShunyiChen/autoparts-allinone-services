package com.shunyi.autoparts.exception;

/** 品牌系列找不到异常 */
public class BrandSeriesNotFoundException extends RuntimeException {

    public BrandSeriesNotFoundException(String exception) {
        super(exception);
    }
}
