package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.SKUDao;
import com.shunyi.autoparts.exception.SKUNotFoundException;
import com.shunyi.autoparts.model.SKU;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @description SKU控制器
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@RestController
@CrossOrigin
public class SKUController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(SKUController.class);
    @Autowired
    private SKUDao skuDao;

    @PostMapping("/sku")
    public ResponseEntity<?> create(@RequestBody SKU sku) {
        sku.setDateCreated(new Date());
        SKU savedSKU = skuDao.save(sku);
        return new ResponseEntity<>(savedSKU.getId(), HttpStatus.OK);
    }

    @PutMapping("/sku/{id}")
    public ResponseEntity<?> update(@RequestBody SKU sku, @PathVariable Long id) {
        Optional<SKU> skuOptional = skuDao.findById(id);
        if (!skuOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        sku.setId(id);
        sku.setDateUpdated(new Date());
        sku.setUpdatedCount(sku.getUpdatedCount() == null? 1 : sku.getUpdatedCount() + 1);
        skuDao.save(sku);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/sku/{id}")
    public void delete(@PathVariable Long id) {
        skuDao.deleteById(id);
    }

    @GetMapping("/sku")
    public List<SKU> retrieveAll() {
        return skuDao.findAll();
    }

    @GetMapping("/sku/products/{pid}")
    public List<SKU> retrieveAllByProductId(@PathVariable Long pid) {
        return skuDao.findAllByProduct_idOrderByIdAsc(pid);
    }

    @GetMapping("/sku/{id}")
    public SKU retrieve(@PathVariable Long id) {
        Optional<SKU> sku = skuDao.findById(id);
        if (!sku.isPresent()) {
            throw new SKUNotFoundException("SKU not found with id -" + id);
        }
        return sku.get();
    }
}