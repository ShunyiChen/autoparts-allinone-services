package com.shunyi.autoparts.exception;

/** 产品库存找不到异常 */
public class StockNotFoundException extends RuntimeException {

    public StockNotFoundException(String exception) {
        super(exception);
    }
}
