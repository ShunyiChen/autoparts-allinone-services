package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.AttributeNameDao;
import com.shunyi.autoparts.dao.AttributeValueDao;
import com.shunyi.autoparts.exception.AttributeNameNotFoundException;
import com.shunyi.autoparts.model.AttributeName;
import com.shunyi.autoparts.model.AttributeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @description 属性名控制器
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@RestController
@CrossOrigin
public class AttributeNameController {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(AttributeNameController.class);

    @Autowired
    private AttributeNameDao attributeNameDao;

    @Autowired
    private AttributeValueDao attributeValueDao;

    @PostMapping("/attributes/name")
    public ResponseEntity<?> create(@RequestBody AttributeName attributeName) {
        attributeName.setDateCreated(new Date());
        AttributeName savedAttributeName = attributeNameDao.save(attributeName);
        return new ResponseEntity<>(savedAttributeName.getId(), HttpStatus.OK);
    }

    @PutMapping("/attributes/name/{id}")
    public ResponseEntity<?> update(@RequestBody AttributeName attributeName, @PathVariable Long id) {
        Optional<AttributeName> attributeOptional = attributeNameDao.findById(id);
        if (!attributeOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        attributeName.setId(id);
        attributeNameDao.save(attributeName);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/attributes/name/{id}")
    public void delete(@PathVariable Long id) {
        attributeNameDao.deleteById(id);
    }

    @DeleteMapping("/attributes/name/children/{id}")
    public void deleteCurrentAndAllChildren(@PathVariable Long id) {
        //查询属性值列表通过属性名ID
        List<AttributeValue> values = attributeValueDao.findAllByAttributeName_idOrderByIdAsc(id);
        //删除属性值列表
        attributeValueDao.deleteAll(values);
        //删除当前属性名
        attributeNameDao.deleteById(id);
    }

    @GetMapping("/attributes/name")
    public List<AttributeName> retrieveAll() {
        return attributeNameDao.findAll();
    }

    @GetMapping("/attributes/name/{id}")
    public AttributeName retrieve(@PathVariable Long id) {
        Optional<AttributeName> attribute = attributeNameDao.findById(id);
        if (!attribute.isPresent()) {
            throw new AttributeNameNotFoundException("BasicAttributes name not found with id -" + id);
        }
        return attribute.get();
    }

    @GetMapping("/attributes/name/category/{pid}")
    public List<AttributeName> retrieveAllByCategory(@PathVariable Long pid) {
        return attributeNameDao.findAllByCategory_idOrderByIdAsc(pid);
    }
}
