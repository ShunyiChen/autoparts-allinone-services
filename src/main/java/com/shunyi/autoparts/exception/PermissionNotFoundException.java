package com.shunyi.autoparts.exception;

/**
 * @description 权限找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class PermissionNotFoundException extends RuntimeException {

    public PermissionNotFoundException(String exception) {
        super(exception);
    }
}
