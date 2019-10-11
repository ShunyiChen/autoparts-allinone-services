package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.AttributeNameDao;
import com.shunyi.autoparts.exception.AttributeNameNotFoundException;
import com.shunyi.autoparts.model.AttributeName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/** 属性名控制器 */
@RestController
@CrossOrigin
public class AttributeNameController {
    @Autowired
    private AttributeNameDao attributeNameDao;

    @PostMapping("/attributes/name")
    public ResponseEntity<?> create(@RequestBody AttributeName attributeName) {
        AttributeName savedAttributeName = attributeNameDao.save(attributeName);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAttributeName.getId()).toUri();
        return ResponseEntity.created(location).build();
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
}