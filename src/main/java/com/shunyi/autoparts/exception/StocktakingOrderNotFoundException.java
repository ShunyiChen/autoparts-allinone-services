package com.shunyi.autoparts.exception;

/**
 * @description 盘点单找不到异常
 * @author Shunyi
 * @date 2020/5/14
 */
public class StocktakingOrderNotFoundException extends RuntimeException {
    public StocktakingOrderNotFoundException(String exception) {
        super(exception);
    }
}
