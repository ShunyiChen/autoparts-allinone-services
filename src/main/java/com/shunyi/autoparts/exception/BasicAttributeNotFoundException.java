package com.shunyi.autoparts.exception;

/**
 * @description 基本属性找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class BasicAttributeNotFoundException extends RuntimeException {

    public BasicAttributeNotFoundException(String exception) {
        super(exception);
    }
}
