package com.shunyi.autoparts.exception;

/**
 * @description 产品Logo找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class LogoNotFoundException extends RuntimeException {

    public LogoNotFoundException(String exception) {
        super(exception);
    }
}
