package com.shunyi.autoparts.exception;

/**
 * @description 调价单明细找不到异常
 * @author Shunyi
 * @date 2020/5/15
 */
public class PriceAdjustmentOrderItemNotFoundException extends RuntimeException {

    public PriceAdjustmentOrderItemNotFoundException(String exception) {
        super(exception);
    }
}
