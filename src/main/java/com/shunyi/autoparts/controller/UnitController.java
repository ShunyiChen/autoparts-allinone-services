package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.UnitDao;
import com.shunyi.autoparts.exception.UnitNotFoundException;
import com.shunyi.autoparts.model.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @description 配件产地控制器
 * @author Shunyi Chen
 * @date 2020/4/7
 */
@RestController
@CrossOrigin
public class UnitController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(UnitController.class);
    @Autowired
    private UnitDao unitDao;

    @PostMapping("/units")
    public ResponseEntity<?> create(@RequestBody Unit place) {
        Unit savedCar = unitDao.save(place);
        return new ResponseEntity<>(savedCar.getId(), HttpStatus.OK);
    }

    @PutMapping("/units/{id}")
    public ResponseEntity<?> update(@RequestBody Unit place, @PathVariable Long id) {
        Optional<Unit> carOptional = unitDao.findById(id);
        if (!carOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        place.setId(id);
        unitDao.save(place);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/units/{id}")
    public void delete(@PathVariable Long id) {
        unitDao.deleteById(id);
    }

    @GetMapping("/units")
    public List<Unit> retrieveAll() {
        return unitDao.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/units/{id}")
    public Unit retrieve(@PathVariable Long id) {
        Optional<Unit> place = unitDao.findById(id);
        if (!place.isPresent()) {
            throw new UnitNotFoundException("Unit not found with id -" + id);
        }
        return place.get();
    }
}