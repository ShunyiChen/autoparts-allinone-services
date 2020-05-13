package com.shunyi.autoparts.exception;

/**
 * @description 销售退货单找不到异常
 * @author Shunyi
 * @date 2020/5/13
 */
public class SalesReturnOrderNotFoundException extends RuntimeException {

    public SalesReturnOrderNotFoundException(String exception) {
        super(exception);
    }
}
