package com.shunyi.autoparts.exception;

/** 产品图片找不到异常 */
public class PictureNotFoundException extends RuntimeException {

    public PictureNotFoundException(String exception) {
        super(exception);
    }
}
