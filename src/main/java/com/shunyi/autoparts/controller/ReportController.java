package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.ReportDao;
import com.shunyi.autoparts.model.Report;
import com.shunyi.autoparts.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @description 进货单账号控制器
 * @author Shunyi Chen
 * @date 2020/4/18
 */
@RestController
@CrossOrigin
public class ReportController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    @Autowired
    private ReportDao reportDao;
    @Autowired
    private ReportService reportService;

    @PostMapping("/reports")
    public ResponseEntity<?> create(@RequestBody Report report) {
        List<Report> reports = reportDao.findAll();
        Optional<Report> findAny = reports.parallelStream().filter(c -> c.getOrderNo().equals(report.getOrderNo())).findAny();
        if(!findAny.isPresent()) {
            reportService.generate(report.getOrderNo(), report.getTemplateName(), report.getReportFileType());
            report.setDateCreated(new Date());
            Report savedReport = reportDao.save(report);
            return new ResponseEntity<>(savedReport.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>(findAny.get().getId(), HttpStatus.OK);
    }

    @DeleteMapping("/reports")
    public void delete(@RequestBody Report report) {
        reportService.delete(report.getOrderNo(), report.getReportFileType());
        reportDao.deleteByOrderNoAndReportFileType(report.getOrderNo(), report.getReportFileType());
    }

    @GetMapping("/reports")
    public List<Report> retrieveAll() {
        return reportDao.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/report/templates")
    public List<String> retrieveAllTemplates() {
        return reportService.findTempletes();
    }

}