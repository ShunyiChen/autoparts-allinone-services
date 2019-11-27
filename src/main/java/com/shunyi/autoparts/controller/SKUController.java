package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.SKUDao;
import com.shunyi.autoparts.exception.SKUNotFoundException;
import com.shunyi.autoparts.model.AttributeValue;
import com.shunyi.autoparts.model.SKU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/** 产品SKU控制器 */
@RestController
@CrossOrigin
public class SKUController {

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
        if (!skuOptional.isPresent())
            return ResponseEntity.notFound().build();
        sku.setId(id);
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

//    @PostMapping("/sku/products/properties")
//    public SKU retrieveAllByProductIdAndProperties(@RequestBody SKU sku) {
//        return skuDao.findByProduct_idAndPropertiesOrderByIdAsc(sku.getProduct().getId(), sku.getProperties());
//    }

    @GetMapping("/sku/{id}")
    public SKU retrieve(@PathVariable Long id) {
        Optional<SKU> sku = skuDao.findById(id);
        if (!sku.isPresent())
            throw new SKUNotFoundException("SKU not found with id -" + id);
        return sku.get();
    }
}