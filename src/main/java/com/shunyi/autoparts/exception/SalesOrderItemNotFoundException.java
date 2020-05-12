package com.shunyi.autoparts.exception;

/**
 * @description 销售单明细找不到异常
 * @author Shunyi
 * @date 2020/5/12
 */
public class SalesOrderItemNotFoundException extends RuntimeException {
    public SalesOrderItemNotFoundException(String exception) {
        super(exception);
    }
}
