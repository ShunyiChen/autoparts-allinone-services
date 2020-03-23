package com.shunyi.autoparts.exception;

/**
 * @description 品牌系列找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class BrandSeriesNotFoundException extends RuntimeException {

    public BrandSeriesNotFoundException(String exception) {
        super(exception);
    }
}
