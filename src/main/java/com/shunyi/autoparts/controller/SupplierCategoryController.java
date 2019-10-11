package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.SupplierCategoryDao;
import com.shunyi.autoparts.exception.SupplierCategoryNotFoundException;
import com.shunyi.autoparts.model.SupplierCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/** 供应商类目控制器 */
@RestController
@CrossOrigin
public class SupplierCategoryController {

    @Autowired
    private SupplierCategoryDao supplierCategoryDao;

    @PostMapping("/supplier/categories")
    public ResponseEntity<?> create(@RequestBody SupplierCategory supplierCategory) {
        SupplierCategory savedCategory = supplierCategoryDao.save(supplierCategory);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedCategory.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/supplier/categories/{id}")
    public ResponseEntity<?> update(@RequestBody SupplierCategory supplierCategory, @PathVariable Long id) {
        Optional<SupplierCategory> categoryOptional = supplierCategoryDao.findById(id);
        if (!categoryOptional.isPresent())
            return ResponseEntity.notFound().build();
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
        return supplierCategoryDao.findAll();
    }

    @GetMapping("/supplier/categories/{id}")
    public SupplierCategory retrieve(@PathVariable Long id) {
        Optional<SupplierCategory> supplierCategory = supplierCategoryDao.findById(id);
        if (!supplierCategory.isPresent())
            throw new SupplierCategoryNotFoundException("Supplier category not found with id -" + id);
        return supplierCategory.get();
    }
}
