package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.LogoDao;
import com.shunyi.autoparts.exception.LogoNotFoundException;
import com.shunyi.autoparts.model.Logo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/** 产品Logo控制器 */
@RestController
@CrossOrigin
public class LogoController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(LogoController.class);
    @Autowired
    private LogoDao logoDao;

    @PostMapping("/logos")
    public ResponseEntity<?> create(@RequestBody Logo logo) {
        Logo savedLogo = logoDao.save(logo);
        return new ResponseEntity<>(savedLogo.getId(), HttpStatus.OK);
    }

    @PutMapping("/logos/{id}")
    public ResponseEntity<?> update(@RequestBody Logo logo, @PathVariable Long id) {
        Optional<Logo> logoOptional = logoDao.findById(id);
        if (!logoOptional.isPresent())
            return ResponseEntity.notFound().build();
        logo.setId(id);
        logoDao.save(logo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/logos/{id}")
    public void delete(@PathVariable Long id) {
        logoDao.deleteById(id);
    }

    @GetMapping("/logos")
    public List<Logo> retrieveAll() {
        return logoDao.findAll();
    }

    @GetMapping("/logos/{id}")
    public Logo retrieve(@PathVariable Long id) {
        Optional<Logo> logo = logoDao.findById(id);
        if (!logo.isPresent())
            throw new LogoNotFoundException("Logo not found with id -" + id);
        return logo.get();
    }
}