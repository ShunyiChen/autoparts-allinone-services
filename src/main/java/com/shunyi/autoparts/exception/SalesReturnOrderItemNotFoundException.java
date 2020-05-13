package com.shunyi.autoparts.exception;

/**
 * @description 销售退货单明细找不到异常
 * @author Shunyi
 * @date 2020/5/13
 */
public class SalesReturnOrderItemNotFoundException extends RuntimeException {

    public SalesReturnOrderItemNotFoundException(String exception) {
        super(exception);
    }
}
