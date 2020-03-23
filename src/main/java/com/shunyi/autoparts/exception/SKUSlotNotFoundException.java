package com.shunyi.autoparts.exception;

/** SKU与货位映射关系找不到异常 */
public class SKUCargoSpaceNotFoundException extends RuntimeException {

    public SKUCargoSpaceNotFoundException(String exception) {
        super(exception);
    }
}
