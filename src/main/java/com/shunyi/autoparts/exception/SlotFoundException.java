package com.shunyi.autoparts.exception;

/**
 * @description 货位找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class SlotFoundException extends RuntimeException {

    public SlotFoundException(String exception) {
        super(exception);
    }
}
