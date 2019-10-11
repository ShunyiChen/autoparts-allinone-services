package com.shunyi.autoparts.exception;

/** 打包品找不到异常 */
public class PackageNotFoundException extends RuntimeException {

    public PackageNotFoundException(String exception) {
        super(exception);
    }
}
