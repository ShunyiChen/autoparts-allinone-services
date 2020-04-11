package com.shunyi.autoparts.exception;

/**
 * @description 所属公司找不到异常
 * @author Shunyi Chen
 * @date 2020/4/11
 */
public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(String exception) {
        super(exception);
    }
}
