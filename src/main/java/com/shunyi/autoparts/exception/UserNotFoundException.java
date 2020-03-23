package com.shunyi.autoparts.exception;

/**
 * @description 用户找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String exception) {
        super(exception);
    }
}
