package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.BasicAttributeDao;
import com.shunyi.autoparts.exception.BasicAttributeNotFoundException;
import com.shunyi.autoparts.model.BasicAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @description 基本属性控制器
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@RestController
@CrossOrigin
public class BasicAttributeController {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(BasicAttributeController.class);
    
    @Autowired
    private BasicAttributeDao basicAttributeDao;

    @PostMapping("/basic/attributes")
    public ResponseEntity<?> create(@RequestBody BasicAttributes attribute) {
        BasicAttributes savedAttribute = basicAttributeDao.save(attribute);
        return new ResponseEntity<>(savedAttribute.getId(), HttpStatus.OK);
    }

    @PutMapping("/basic/attributes/{id}")
    public ResponseEntity<?> update(@RequestBody BasicAttributes attribute, @PathVariable Long id) {
        Optional<BasicAttributes> attributeOptional = basicAttributeDao.findById(id);
        if (!attributeOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        attribute.setId(id);
        basicAttributeDao.save(attribute);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/basic/attributes/{id}")
    public void delete(@PathVariable Long id) {
        basicAttributeDao.deleteById(id);
    }

    @DeleteMapping("/basic/attributes/{productId}/{attributeNameId}")
    public void delete(@PathVariable Long productId, @PathVariable Long attributeNameId) {
        List<BasicAttributes> attributes = basicAttributeDao.findByProduct_idAndAttributeNameId(productId, attributeNameId);
        basicAttributeDao.deleteAll(attributes);
    }

    @GetMapping("/basic/attributes")
    public List<BasicAttributes> retrieveAll() {
        return basicAttributeDao.findAll();
    }

    @GetMapping("/basic/attributes/{id}")
    public BasicAttributes retrieve(@PathVariable Long id) {
        Optional<BasicAttributes> attribute = basicAttributeDao.findById(id);
        if (!attribute.isPresent()) {
            logger.error("BasicAttributes not found with id " + id);
            throw new BasicAttributeNotFoundException("BasicAttributes not found with id " + id);
        }
        return attribute.get();
    }

    @GetMapping("/basic/attributes/products/{pid}")
    public List<BasicAttributes> retrieveAllByProductId(@PathVariable Long pid) {
        return basicAttributeDao.findByProduct_id(pid);
    }
}
