package com.shunyi.autoparts.exception;

/**
 * @description 属性名找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class AttributeNameNotFoundException extends RuntimeException {

    public AttributeNameNotFoundException(String exception) {
        super(exception);
    }
}
