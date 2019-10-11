package com.shunyi.autoparts.exception;

/** VFS找不到异常 */
public class VFSNotFoundException extends RuntimeException {

    public VFSNotFoundException(String exception) {
        super(exception);
    }
}
