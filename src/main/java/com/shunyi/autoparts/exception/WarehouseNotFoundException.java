package com.shunyi.autoparts.exception;

/** 仓库找不到异常 */
public class WarehouseNotFoundException extends RuntimeException {

    public WarehouseNotFoundException(String exception) {
        super(exception);
    }
}
