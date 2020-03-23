package com.shunyi.autoparts.exception;

/**
 * @description 属性值找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class AttributeValueNotFoundException extends RuntimeException {

    public AttributeValueNotFoundException(String exception) {
        super(exception);
    }
}
