package com.shunyi.autoparts.exception;

/** 店铺找不到异常 */
public class ShopNotFoundException extends RuntimeException {

    public ShopNotFoundException(String exception) {
        super(exception);
    }
}
