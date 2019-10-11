package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.AttributeDao;
import com.shunyi.autoparts.exception.AttributeNotFoundException;
import com.shunyi.autoparts.model.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/** 产品基本属性控制器 */
@RestController
@CrossOrigin
public class AttributeController {
    @Autowired
    private AttributeDao attributeDao;

    @PostMapping("/attributes")
    public ResponseEntity<?> create(@RequestBody Attribute attribute) {
        Attribute savedAttribute = attributeDao.save(attribute);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAttribute.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/attributes/{id}")
    public ResponseEntity<?> update(@RequestBody Attribute attribute, @PathVariable Long id) {
        Optional<Attribute> attributeOptional = attributeDao.findById(id);
        if (!attributeOptional.isPresent())
            return ResponseEntity.notFound().build();
        attribute.setId(id);
        attributeDao.save(attribute);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/attributes/{id}")
    public void delete(@PathVariable Long id) {
        attributeDao.deleteById(id);
    }

    @GetMapping("/attributes")
    public List<Attribute> retrieveAll() {
        return attributeDao.findAll();
    }

    @GetMapping("/attributes/{id}")
    public Attribute retrieve(@PathVariable Long id) {
        Optional<Attribute> attribute = attributeDao.findById(id);
        if (!attribute.isPresent())
            throw new AttributeNotFoundException("Attribute not found with id -" + id);
        return attribute.get();
    }
}