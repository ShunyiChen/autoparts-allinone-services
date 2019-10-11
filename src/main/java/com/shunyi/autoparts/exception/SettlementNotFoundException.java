package com.shunyi.autoparts.exception;

/** 结算方式找不到异常 */
public class SettlementNotFoundException extends RuntimeException {

    public SettlementNotFoundException(String exception) {
        super(exception);
    }
}
