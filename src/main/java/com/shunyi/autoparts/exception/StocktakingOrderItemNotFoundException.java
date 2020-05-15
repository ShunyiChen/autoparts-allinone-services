package com.shunyi.autoparts.exception;

/**
 * @description 盘点单明细找不到异常
 * @author Shunyi
 * @date 2020/5/14
 */
public class StocktakingOrderItemNotFoundException extends RuntimeException {
    public StocktakingOrderItemNotFoundException(String exception) {
        super(exception);
    }
}
