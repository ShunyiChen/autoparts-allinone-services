package com.shunyi.autoparts.exception;

/** 产品类目找不到异常 */
public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String exception) {
        super(exception);
    }

}
