package com.shunyi.autoparts.exception;

/** 权限找不到异常 */
public class PermissionNotFoundException extends RuntimeException {

    public PermissionNotFoundException(String exception) {
        super(exception);
    }
}
