package com.shunyi.autoparts.exception;

/**
 * @description SKU图片找不到异常
 * @author Shunyi Chen
 * @date 2020/4/2
 */
public class SKUPhotoNotFoundException extends RuntimeException {
    public SKUPhotoNotFoundException(String exception) {
        super(exception);
    }
}
