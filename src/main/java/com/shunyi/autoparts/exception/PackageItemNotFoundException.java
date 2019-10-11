package com.shunyi.autoparts.exception;

/** 打包品余项找不到异常 */
public class PackageItemNotFoundException extends RuntimeException {

    public PackageItemNotFoundException(String exception) {
        super(exception);
    }
}
