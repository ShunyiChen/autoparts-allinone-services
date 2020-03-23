package com.shunyi.autoparts.exception;

/**
 * @description 产品类目找不到异常
 * @author Shunyi Chen
 * @date 2020/3/23
 */
public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String exception) {
        super(exception);
    }
}
