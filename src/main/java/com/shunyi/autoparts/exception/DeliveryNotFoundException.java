package com.shunyi.autoparts.exception;

/**
 * @description 发货方式找不到异常
 * @author Shunyi
 * @date 2020/5/12
 */
public class DeliveryNotFoundException extends RuntimeException {
    public DeliveryNotFoundException(String exception) {
        super(exception);
    }
}
