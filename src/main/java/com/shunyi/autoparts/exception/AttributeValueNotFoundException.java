package com.shunyi.autoparts.exception;

/** 属性值找不到异常 */
public class AttributeValueNotFoundException extends RuntimeException {

    public AttributeValueNotFoundException(String exception) {
        super(exception);
    }
}
