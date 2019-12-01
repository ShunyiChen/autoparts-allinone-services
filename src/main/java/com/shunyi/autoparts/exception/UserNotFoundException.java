package com.shunyi.autoparts.exception;

/** 用户找不到异常 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String exception) {
        super(exception);
    }
}
