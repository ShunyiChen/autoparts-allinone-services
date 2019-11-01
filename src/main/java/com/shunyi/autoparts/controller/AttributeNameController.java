package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.AttributeNameDao;
import com.shunyi.autoparts.exception.AttributeNameNotFoundException;
import com.shunyi.autoparts.model.AttributeName;
import com.shunyi.autoparts.model.Supplier;
import com.shunyi.autoparts.model.SupplierCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.criteria.*;
import java.net.URI;
import java.util.*;

/** 属性名控制器 */
@RestController
@CrossOrigin
public class AttributeNameController {
    @Autowired
    private AttributeNameDao attributeNameDao;

    @PostMapping("/attributes/name")
    public ResponseEntity<?> create(@RequestBody AttributeName attributeName) {
        AttributeName savedAttributeName = attributeNameDao.save(attributeName);
        return new ResponseEntity<>(savedAttributeName.getId(), HttpStatus.OK);
    }

    @PutMapping("/attributes/name/{id}")
    public ResponseEntity<?> update(@RequestBody AttributeName attributeName, @PathVariable Long id) {
        Optional<AttributeName> attributeOptional = attributeNameDao.findById(id);
        if (!attributeOptional.isPresent())
            return ResponseEntity.notFound().build();
        attributeName.setId(id);
        attributeNameDao.save(attributeName);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/attributes/name/{id}")
    public void delete(@PathVariable Long id) {
        attributeNameDao.deleteById(id);
    }

    @GetMapping("/attributes/name")
    public List<AttributeName> retrieveAll() {
        return attributeNameDao.findAll();
    }

    @GetMapping("/attributes/name/{id}")
    public AttributeName retrieve(@PathVariable Long id) {
        Optional<AttributeName> attribute = attributeNameDao.findById(id);
        if (!attribute.isPresent())
            throw new AttributeNameNotFoundException("Attribute name not found with id -" + id);
        return attribute.get();
    }

    @GetMapping("/attributes/name/category/{pid}")
    public List<AttributeName> retrieveAllByCategory(@PathVariable Long pid) {
        return attributeNameDao.findAllByCategory_idOrderByIdAsc(pid);
    }
}
