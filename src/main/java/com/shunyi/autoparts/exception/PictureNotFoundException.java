package com.shunyi.autoparts.exception;

/**
 * @description 产品图片找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class PictureNotFoundException extends RuntimeException {

    public PictureNotFoundException(String exception) {
        super(exception);
    }
}
