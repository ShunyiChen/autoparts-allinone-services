package com.shunyi.autoparts.exception;

/** 公司找不到异常 */
public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException(String exception) {
        super(exception);
    }
}
