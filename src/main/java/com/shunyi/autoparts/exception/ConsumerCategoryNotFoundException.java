package com.shunyi.autoparts.exception;

/**
 * @description 客户分类找不到异常
 * @author Shunyi
 * @date 2020/5/12
 */
public class ConsumerCategoryNotFoundException extends RuntimeException {
    public ConsumerCategoryNotFoundException(String exception) {
        super(exception);
    }
}
