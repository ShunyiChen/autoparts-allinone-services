package com.shunyi.autoparts.exception;

/**
 * @description 角色找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(String exception) {
        super(exception);
    }
}
