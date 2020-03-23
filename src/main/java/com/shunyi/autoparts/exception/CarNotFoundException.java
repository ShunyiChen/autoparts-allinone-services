package com.shunyi.autoparts.exception;

/**
 * @description 车型类目找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(String exception) {
        super(exception);
    }
}
