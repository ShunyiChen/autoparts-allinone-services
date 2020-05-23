package com.shunyi.autoparts.exception;

/**
 * @Description: 导出html报表异常
 * @Author: Shunyi
 * @CreateDate: 2020/5/23
 */
public class ExportReportToHtmlFileException extends RuntimeException {
    public ExportReportToHtmlFileException(String exception) {
        super(exception);
    }
}
