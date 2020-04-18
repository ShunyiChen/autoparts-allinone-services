package com.shunyi.autoparts.exception;

/**
 * @description 结算方式找不到异常
 * @author Shunyi Chen
 * @date 2020/4/18
 */
public class PaymentNotFoundException extends RuntimeException {

    public PaymentNotFoundException(String exception) {
        super(exception);
    }
}
