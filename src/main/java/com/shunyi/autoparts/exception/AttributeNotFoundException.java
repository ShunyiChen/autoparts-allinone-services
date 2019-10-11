package com.shunyi.autoparts.exception;

/** 产品基本属性找不到异常 */
public class AttributeNotFoundException extends RuntimeException {

    public AttributeNotFoundException(String exception) {
        super(exception);
    }
}
