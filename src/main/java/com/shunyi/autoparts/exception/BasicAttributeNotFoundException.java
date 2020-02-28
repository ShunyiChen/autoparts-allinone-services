package com.shunyi.autoparts.exception;

/** 产品基本属性找不到异常 */
public class BasicAttributeNotFoundException extends RuntimeException {

    public BasicAttributeNotFoundException(String exception) {
        super(exception);
    }
}
