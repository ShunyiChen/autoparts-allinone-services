package com.shunyi.autoparts.exception;

/**
 * @description 销售单找不到异常
 * @author Shunyi
 * @date 2020/5/12
 */
public class SalesOrderNotFoundException extends RuntimeException {
    public SalesOrderNotFoundException(String exception) {
        super(exception);
    }
}
