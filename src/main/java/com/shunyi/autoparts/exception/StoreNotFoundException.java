package com.shunyi.autoparts.exception;

/**
 * @description 店铺找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class StoreNotFoundException extends RuntimeException {

    public StoreNotFoundException(String exception) {
        super(exception);
    }
}
