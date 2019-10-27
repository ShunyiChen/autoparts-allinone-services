package com.shunyi.autoparts.exception;

/** 车型类目找不到异常 */
public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(String exception) {
        super(exception);
    }
}
