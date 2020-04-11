package com.shunyi.autoparts.exception;

/**
 * @description 配件进口找不到异常
 * @author Shunyi Chen
 * @date 2020/4/11
 */
public class ImportNotFoundException extends RuntimeException {
    public ImportNotFoundException(String exception) {
        super(exception);
    }
}
