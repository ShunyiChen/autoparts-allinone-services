package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.AttributeValueDao;
import com.shunyi.autoparts.exception.AttributeValueNotFoundException;
import com.shunyi.autoparts.model.AttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/** 属性值控制器 */
@RestController
@CrossOrigin
public class AttributeValueController {
    @Autowired
    private AttributeValueDao attributeValueDao;

    @PostMapping("/attributes/value")
    public ResponseEntity<?> create(@RequestBody AttributeValue attributeValue) {
        AttributeValue savedAttributeValue = attributeValueDao.save(attributeValue);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAttributeValue.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/attributes/value/{id}")
    public ResponseEntity<?> update(@RequestBody AttributeValue attributeValue, @PathVariable Long id) {
        Optional<AttributeValue> attributeOptional = attributeValueDao.findById(id);
        if (!attributeOptional.isPresent())
            return ResponseEntity.notFound().build();
        attributeValue.setId(id);
        attributeValueDao.save(attributeValue);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/attributes/value/{id}")
    public void delete(@PathVariable Long id) {
        attributeValueDao.deleteById(id);
    }

    @GetMapping("/attributes/value")
    public List<AttributeValue> retrieveAll() {
        return attributeValueDao.findAll();
    }

    @GetMapping("/attributes/value/{id}")
    public AttributeValue retrieve(@PathVariable Long id) {
        Optional<AttributeValue> attribute = attributeValueDao.findById(id);
        if (!attribute.isPresent())
            throw new AttributeValueNotFoundException("Attribute value not found with id -" + id);
        return attribute.get();
    }
}
