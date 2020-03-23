package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.SKUSlotDao;
import com.shunyi.autoparts.exception.SKUSlotNotFoundException;
import com.shunyi.autoparts.model.SKUSlotMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @description SKU与货位关系控制器
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@RestController
@CrossOrigin
public class SKUSlotMappingController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(SKUSlotMappingController.class);
    @Autowired
    private SKUSlotDao skuSlotDao;

    @PostMapping("/skuSlotMappings")
    public ResponseEntity<?> create(@RequestBody SKUSlotMapping skuSlot) {
        SKUSlotMapping savedSKUSlot = skuSlotDao.save(skuSlot);
        return new ResponseEntity<>(savedSKUSlot.getId(), HttpStatus.OK);
    }

    @PutMapping("/skuSlotMappings/{id}")
    public ResponseEntity<?> update(@RequestBody SKUSlotMapping skuSlot, @PathVariable SKUSlotMapping.Id id) {
        Optional<SKUSlotMapping> skuSlotOptional = skuSlotDao.findById(id);
        if (!skuSlotOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        skuSlot.setId(id);
        skuSlotDao.save(skuSlot);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/skuSlotMappings/{id}")
    public void delete(@PathVariable SKUSlotMapping.Id id) {
        skuSlotDao.deleteById(id);
    }

    @GetMapping("/skuSlotMappings")
    public List<SKUSlotMapping> retrieveAll() {
        return skuSlotDao.findAll();
    }

    @GetMapping("/skuSlotMappings/{id}")
    public SKUSlotMapping retrieve(@PathVariable SKUSlotMapping.Id id) {
        Optional<SKUSlotMapping> skuSlot = skuSlotDao.findById(id);
        if (!skuSlot.isPresent()) {
            throw new SKUSlotNotFoundException("SKUSlotMapping not found with id - " + id);
        }
        return skuSlot.get();
    }
}