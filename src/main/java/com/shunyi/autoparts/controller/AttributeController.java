package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.AttributeDao;
import com.shunyi.autoparts.exception.AttributeNotFoundException;
import com.shunyi.autoparts.model.Attribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/** 产品基本属性控制器 */
@RestController
@CrossOrigin
public class AttributeController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(AttributeController.class);
    @Autowired
    private AttributeDao attributeDao;

    @PostMapping("/attributes")
    public ResponseEntity<?> create(@RequestBody Attribute attribute) {
        attribute.setDateCreated(new Date());
        Attribute savedAttribute = attributeDao.save(attribute);
        return new ResponseEntity<>(savedAttribute.getId(), HttpStatus.OK);
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

    @DeleteMapping("/attributes/{pid}/{aid}")
    public void delete(@PathVariable Long pid, @PathVariable Long aid) {
        List<Attribute> attributes = attributeDao.findByProduct_idAndAttributeValueId(pid, aid);
        attributeDao.deleteAll(attributes);
    }

    @GetMapping("/attributes")
    public List<Attribute> retrieveAll() {
        return attributeDao.findAll();
    }

    @GetMapping("/attributes/{id}")
    public Attribute retrieve(@PathVariable Long id) {
        Optional<Attribute> attribute = attributeDao.findById(id);
        if (!attribute.isPresent()) {
            logger.error("Attribute not found with id " + id);
            throw new AttributeNotFoundException("Attribute not found with id " + id);
        }
        return attribute.get();
    }

    @GetMapping("/attributes/products/{pid}")
    public List<Attribute> retrieveAllByProductId(@PathVariable Long pid) {
        return attributeDao.findByProduct_id(pid);
    }
}
