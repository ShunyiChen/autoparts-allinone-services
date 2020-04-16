package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.SupplierCategoryDao;
import com.shunyi.autoparts.exception.SupplierCategoryNotFoundException;
import com.shunyi.autoparts.model.SupplierCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @description 供应商类目控制器
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@RestController
@CrossOrigin
public class SupplierCategoryController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(SupplierCategoryController.class);
    @Autowired
    private SupplierCategoryDao supplierCategoryDao;

    @PostMapping("/supplier/categories")
    public ResponseEntity<Long> create(@RequestBody SupplierCategory supplierCategory) {
        List<SupplierCategory> categories = supplierCategoryDao.findAll();
        Optional<SupplierCategory> findAny = categories.parallelStream().filter(c -> c.getName().equals(supplierCategory.getName())).findAny();
        if(!findAny.isPresent()) {
            SupplierCategory savedCategory = supplierCategoryDao.save(supplierCategory);
            return new ResponseEntity<>(savedCategory.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>(findAny.get().getId(), HttpStatus.OK);
    }

    @PutMapping("/supplier/categories/{id}")
    public ResponseEntity<?> update(@RequestBody SupplierCategory supplierCategory, @PathVariable Long id) {
        Optional<SupplierCategory> categoryOptional = supplierCategoryDao.findById(id);
        if (!categoryOptional.isPresent()) {
            return ResponseEntity.notFound().build();

        }
        supplierCategory.setId(id);
        supplierCategoryDao.save(supplierCategory);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/supplier/categories/{id}")
    public void delete(@PathVariable Long id) {
        supplierCategoryDao.deleteById(id);
    }

    @GetMapping("/supplier/categories")
    public List<SupplierCategory> retrieveAll() {
        return supplierCategoryDao.findAllByOrderByIdAsc();
    }

    @GetMapping("/supplier/categories/{id}")
    public SupplierCategory retrieve(@PathVariable Long id) {
        Optional<SupplierCategory> supplierCategory = supplierCategoryDao.findById(id);
        if (!supplierCategory.isPresent()) {
            throw new SupplierCategoryNotFoundException("Supplier category not found with id -" + id);
        }
        return supplierCategory.get();
    }

    @GetMapping("/supplier/categories/root")
    public SupplierCategory retrieveRoot() {
        return supplierCategoryDao.findByParentId(0L);
    }
}
