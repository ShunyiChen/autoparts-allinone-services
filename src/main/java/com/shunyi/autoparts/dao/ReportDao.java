package com.shunyi.autoparts.dao;

import com.shunyi.autoparts.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description 报表Dao
 * @author Shunyi
 * @date 2020/5/23
 */
public interface ReportDao extends JpaRepository<Report, Long> {

    void deleteByOrderNoAndReportFileType(String orderNo, String reportFileType);
}
