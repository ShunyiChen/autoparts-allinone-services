package com.shunyi.autoparts.exception;

/** 货位找不到异常 */
public class CargoSpaceNotFoundException extends RuntimeException {

    public CargoSpaceNotFoundException(String exception) {
        super(exception);
    }
}
