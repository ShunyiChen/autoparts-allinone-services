package com.shunyi.autoparts.exception;

/**
 * @description 客户找不到异常
 * @author Shunyi
 * @date 2020/5/12
 */
public class ConsumerNotFoundException extends RuntimeException {
    public ConsumerNotFoundException(String exception) {
        super(exception);
    }
}
