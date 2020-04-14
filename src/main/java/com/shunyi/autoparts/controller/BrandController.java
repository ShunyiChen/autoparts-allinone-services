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
 * @date 2020/4/13
 */
@RestController
@CrossOrigin
public class BrandController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(BrandController.class);
    @Autowired
    private BrandDao brandDao;

    @PostMapping("/brands")
    public ResponseEntity<?> create(@RequestBody Brand brand) {
        List<Brand> places = brandDao.findAll();
        Optional<Brand> findAny = places.parallelStream().filter(c -> c.getName().equals(brand.getName())).findAny();
        if(!findAny.isPresent()) {
            Brand savedBrand = brandDao.save(brand);
            return new ResponseEntity<>(savedBrand.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>(findAny.get().getId(), HttpStatus.OK);
    }

    @PutMapping("/brands/{id}")
    public ResponseEntity<?> update(@RequestBody Brand brand, @PathVariable Long id) {
        Optional<Brand> brandSeriesOptional = brandDao.findById(id);
        if (!brandSeriesOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        brand.setId(id);
        brandDao.save(brand);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/brands/{id}")
    public void delete(@PathVariable Long id) {
        brandDao.deleteById(id);
    }

    @GetMapping("/brands")
    public List<Brand> retrieveAll() {
        return brandDao.findAll(Sort.by(Sort.Direction.ASC,"id"));
    }

    @GetMapping("/brands/{id}")
    public Brand retrieve(@PathVariable Long id) {
        Optional<Brand> brand = brandDao.findById(id);
        if (!brand.isPresent()) {
            throw new BrandNotFoundException("Brand not found with id -" + id);
        }
        return brand.get();
    }
}
