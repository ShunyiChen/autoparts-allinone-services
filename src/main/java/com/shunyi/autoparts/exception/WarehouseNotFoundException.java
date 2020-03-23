package com.shunyi.autoparts.exception;

/**
 * @description 仓库找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class WarehouseNotFoundException extends RuntimeException {

    public WarehouseNotFoundException(String exception) {
        super(exception);
    }
}
