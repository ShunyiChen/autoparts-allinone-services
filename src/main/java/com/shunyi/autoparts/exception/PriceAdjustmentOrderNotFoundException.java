package com.shunyi.autoparts.exception;

/**
 * @description 调价单找不到异常
 * @author Shunyi
 * @date 2020/5/15
 */
public class PriceAdjustmentOrderNotFoundException extends RuntimeException {

    public PriceAdjustmentOrderNotFoundException(String exception) {
        super(exception);
    }
}
