package com.shunyi.autoparts.exception;

/**
 * @description 信誉等级找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class GradeNotFoundException extends RuntimeException {

    public GradeNotFoundException(String exception) {
        super(exception);
    }
}
