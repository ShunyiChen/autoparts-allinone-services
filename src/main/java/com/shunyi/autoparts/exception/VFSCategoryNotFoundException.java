package com.shunyi.autoparts.exception;

/**
 * @description VFS类目找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class VFSCategoryNotFoundException extends RuntimeException {

    public VFSCategoryNotFoundException(String exception) {
        super(exception);
    }
}