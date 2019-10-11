package com.shunyi.autoparts.exception;

/** 部门找不到异常 */
public class DepartmentNotFoundException extends RuntimeException {

    public DepartmentNotFoundException(String exception) {
        super(exception);
    }
}
