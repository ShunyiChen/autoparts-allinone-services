package com.shunyi.autoparts.exception;

/**
 * @description SKU与货位关系找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class SKUSlotNotFoundException extends RuntimeException {

    public SKUSlotNotFoundException(String exception) {
        super(exception);
    }
}
