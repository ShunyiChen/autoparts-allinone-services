package com.shunyi.autoparts.exception;

/**
 * @Description: 导出pdf报表异常
 * @Author: Shunyi
 * @CreateDate: 2020/5/23
 */
public class ExportReportToPdfStreamException extends RuntimeException {
    public ExportReportToPdfStreamException(String exception) {
        super(exception);
    }
}
