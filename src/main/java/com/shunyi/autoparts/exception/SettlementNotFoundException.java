package com.shunyi.autoparts.exception;

/**
 * @description 结算方式找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class SettlementNotFoundException extends RuntimeException {

    public SettlementNotFoundException(String exception) {
        super(exception);
    }
}
