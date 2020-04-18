package com.shunyi.autoparts.exception;

/**
 * @description 发票类型找不到异常
 * @author Shunyi Chen
 * @date 2020/4/18
 */
public class InvoiceTypeNotFoundException extends RuntimeException {

    public InvoiceTypeNotFoundException(String exception) {
        super(exception);
    }
}
