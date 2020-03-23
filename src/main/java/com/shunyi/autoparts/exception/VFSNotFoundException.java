package com.shunyi.autoparts.exception;

/**
 * @description VFS找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class VFSNotFoundException extends RuntimeException {

    public VFSNotFoundException(String exception) {
        super(exception);
    }
}
