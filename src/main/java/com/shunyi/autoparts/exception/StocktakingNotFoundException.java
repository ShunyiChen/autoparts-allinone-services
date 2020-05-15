package com.shunyi.autoparts.exception;

/**
 * @description 盘点方式找不到异常
 * @author Shunyi
 * @date 2020/5/14
 */
public class StocktakingNotFoundException extends RuntimeException {
    public StocktakingNotFoundException(String exception) {
        super(exception);
    }
}
