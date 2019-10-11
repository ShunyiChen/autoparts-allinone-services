package com.shunyi.autoparts.exception;

/** 属性名找不到异常 */
public class AttributeNameNotFoundException extends RuntimeException {

    public AttributeNameNotFoundException(String exception) {
        super(exception);
    }
}
