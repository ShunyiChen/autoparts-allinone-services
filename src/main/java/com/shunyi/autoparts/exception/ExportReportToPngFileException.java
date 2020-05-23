package com.shunyi.autoparts.exception;

/**
 * @Description: 导出png图片报表异常
 * @Author: Shunyi
 * @CreateDate: 2020/5/23
 */
public class ExportReportToPngFileException extends RuntimeException {
    public ExportReportToPngFileException(String exception) {
        super(exception);
    }
}
