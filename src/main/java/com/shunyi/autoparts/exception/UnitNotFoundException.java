package com.shunyi.autoparts.exception;

/**
 * @description 配件单位找不到异常
 * @author Shunyi Chen
 * @date 2020/4/7
 */
public class UnitNotFoundException extends RuntimeException {
    public UnitNotFoundException(String exception) {
        super(exception);
    }
}
