package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.SupplierDao;
import com.shunyi.autoparts.exception.SupplierNotFoundException;
import com.shunyi.autoparts.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/** 供应商控制器 */
@RestController
@CrossOrigin
public class SupplierController {
    @Autowired
    private SupplierDao supplierDao;

    @PostMapping("/suppliers")
    public ResponseEntity<?> create(@RequestBody Supplier supplier) {
        Supplier savedSupplier = supplierDao.save(supplier);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedSupplier.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/suppliers/{id}")
    public ResponseEntity<?> update(@RequestBody Supplier supplier, @PathVariable Long id) {
        Optional<Supplier> supplierOptional = supplierDao.findById(id);
        if (!supplierOptional.isPresent())
            return ResponseEntity.notFound().build();
        supplier.setId(id);
        supplierDao.save(supplier);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/suppliers/{id}")
    public void delete(@PathVariable Long id) {
        supplierDao.deleteById(id);
    }

    @GetMapping("/suppliers")
    public List<Supplier> retrieveAll() {
        return supplierDao.findAll();
    }

    @GetMapping("/suppliers/{id}")
    public Supplier retrieve(@PathVariable Long id) {
        Optional<Supplier> supplier = supplierDao.findById(id);
        if (!supplier.isPresent())
            throw new SupplierNotFoundException("Supplier not found with id -" + id);
        return supplier.get();
    }
}
