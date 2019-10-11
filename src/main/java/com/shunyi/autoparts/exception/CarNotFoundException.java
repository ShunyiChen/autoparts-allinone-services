package com.shunyi.autoparts.exception;

/** 车型找不到异常 */
public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(String exception) {
        super(exception);
    }
}
