package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.SKUCargoSpaceDao;
import com.shunyi.autoparts.exception.SKUCargoSpaceNotFoundException;
import com.shunyi.autoparts.model.SKUCargoSpaceMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/** 产品类目控制器 */
@RestController
@CrossOrigin
public class SKUCargoSpaceMappingController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(SKUCargoSpaceMappingController.class);
    @Autowired
    private SKUCargoSpaceDao skuCargoSpaceDao;

    @PostMapping("/skuCargoSpaceMappings")
    public ResponseEntity<?> create(@RequestBody SKUCargoSpaceMapping skuCargoSpace) {
        SKUCargoSpaceMapping savedSKUCargoSpace = skuCargoSpaceDao.save(skuCargoSpace);
        return new ResponseEntity<>(savedSKUCargoSpace.getId(), HttpStatus.OK);
    }

    @PutMapping("/skuCargoSpaceMappings/{id}")
    public ResponseEntity<?> update(@RequestBody SKUCargoSpaceMapping skuCargoSpace, @PathVariable SKUCargoSpaceMapping.Id id) {
        Optional<SKUCargoSpaceMapping> skuCargoSpaceOptional = skuCargoSpaceDao.findById(id);
        if (!skuCargoSpaceOptional.isPresent())
            return ResponseEntity.notFound().build();
        skuCargoSpace.setId(id);
        skuCargoSpaceDao.save(skuCargoSpace);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/skuCargoSpaceMappings/{id}")
    public void delete(@PathVariable SKUCargoSpaceMapping.Id id) {
        skuCargoSpaceDao.deleteById(id);
    }

    @GetMapping("/skuCargoSpaceMappings")
    public List<SKUCargoSpaceMapping> retrieveAll() {
        return skuCargoSpaceDao.findAll();
    }

    @GetMapping("/skuCargoSpaceMappings/{id}")
    public SKUCargoSpaceMapping retrieve(@PathVariable SKUCargoSpaceMapping.Id id) {
        Optional<SKUCargoSpaceMapping> skuCargoSpace = skuCargoSpaceDao.findById(id);
        if (!skuCargoSpace.isPresent())
            throw new SKUCargoSpaceNotFoundException("SKUCargoSpaceMapping not found with id - " + id);
        return skuCargoSpace.get();
    }
}