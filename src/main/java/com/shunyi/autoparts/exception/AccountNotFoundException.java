package com.shunyi.autoparts.exception;

/**
 * @description 进货单账号找不到异常
 * @author Shunyi Chen
 * @date 2020/4/18
 */
public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String exception) {
        super(exception);
    }
}
