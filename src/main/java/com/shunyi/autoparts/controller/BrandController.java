package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.BrandDao;
import com.shunyi.autoparts.exception.BrandNotFoundException;
import com.shunyi.autoparts.model.Brand;
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
 * @description 品牌系列控制器
 * @author Shunyi Chen
 * @date 2020/4/2
 */
@RestController
@CrossOrigin
public class BrandController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(BrandController.class);
    @Autowired
    private BrandDao brandSeriesDao;

    @PostMapping("/brand")
    public ResponseEntity<?> create(@RequestBody Brand brand) {
        Brand savedBrandSeries = brandSeriesDao.save(brand);
        return new ResponseEntity<>(savedBrandSeries.getId(), HttpStatus.OK);
    }

    @PutMapping("/brand/{id}")
    public ResponseEntity<?> update(@RequestBody Brand brand, @PathVariable Long id) {
        Optional<Brand> brandSeriesOptional = brandSeriesDao.findById(id);
        if (!brandSeriesOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        brand.setId(id);
        brandSeriesDao.save(brand);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/brand/{id}")
    public void delete(@PathVariable Long id) {
        brandSeriesDao.deleteById(id);
    }

    @GetMapping("/brand")
    public List<Brand> retrieveAll() {
        return brandSeriesDao.findAll(Sort.by(Sort.Direction.ASC,"id"));
    }

    @GetMapping("/brand/{id}")
    public Brand retrieve(@PathVariable Long id) {
        Optional<Brand> brand = brandSeriesDao.findById(id);
        if (!brand.isPresent()) {
            throw new BrandNotFoundException("Brand not found with id -" + id);
        }
        return brand.get();
    }
}
