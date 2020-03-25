package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.GradeDao;
import com.shunyi.autoparts.exception.GradeNotFoundException;
import com.shunyi.autoparts.model.Grade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @description 信誉等级Controller
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@RestController
@CrossOrigin
public class GradeController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(GradeController.class);
    @Autowired
    private GradeDao gradeDao;

    @PostMapping("/grades")
    public ResponseEntity<?> create(@RequestBody Grade grade) {
        Grade savedGrade = gradeDao.save(grade);
        return new ResponseEntity<>(savedGrade.getId(), HttpStatus.OK);
    }

    @PutMapping("/grades/{id}")
    public ResponseEntity<?> update(@RequestBody Grade grade, @PathVariable Long id) {
        Optional<Grade> gradeOptional = gradeDao.findById(id);
        if (!gradeOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        grade.setId(id);
        gradeDao.save(grade);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/grades/{id}")
    public void delete(@PathVariable Long id) {
        gradeDao.deleteById(id);
    }

    @GetMapping("/grades")
    public List<Grade> retrieveAll() {
        return gradeDao.findAll();
    }

    @GetMapping("/grades/{id}")
    public Grade retrieve(@PathVariable Long id) {
        Optional<Grade> grade = gradeDao.findById(id);
        if (!grade.isPresent()) {
            throw new GradeNotFoundException("Grade not found with id -" + id);
        }
        return grade.get();
    }
}