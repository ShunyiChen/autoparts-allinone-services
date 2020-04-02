package com.shunyi.autoparts.exception;

/**
 * @description 配件产地找不到异常
 * @author Shunyi Chen
 * @date 2020/4/2
 */
public class PlaceNotFoundException extends RuntimeException {
    public PlaceNotFoundException(String exception) {
        super(exception);
    }
}
