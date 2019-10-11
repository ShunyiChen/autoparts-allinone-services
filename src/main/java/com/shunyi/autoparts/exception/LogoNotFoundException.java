package com.shunyi.autoparts.exception;

/** 产品Logo找不到异常 */
public class LogoNotFoundException extends RuntimeException {

    public LogoNotFoundException(String exception) {
        super(exception);
    }
}
