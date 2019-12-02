package com.shunyi.autoparts.exception;

/** VFS找不到异常 */
public class VFSCategoryNotFoundException extends RuntimeException {

    public VFSCategoryNotFoundException(String exception) {
        super(exception);
    }
}