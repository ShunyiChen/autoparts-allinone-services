package com.shunyi.autoparts.exception;

/** 角色找不到异常 */
public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(String exception) {
        super(exception);
    }
}
