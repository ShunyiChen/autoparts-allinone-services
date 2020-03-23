package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.AttributeValueDao;
import com.shunyi.autoparts.exception.AttributeValueNotFoundException;
import com.shunyi.autoparts.model.AttributeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.net.URI;
import java.util.*;

/**
 * @description 属性值控制器
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@RestController
@CrossOrigin
public class AttributeValueController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(AttributeValueController.class);
    @Autowired
    private AttributeValueDao attributeValueDao;

    @PostMapping("/attributes/value")
    public ResponseEntity<?> create(@RequestBody AttributeValue attributeValue) {
        attributeValue.setDateCreated(new Date());
        AttributeValue savedAttributeValue = attributeValueDao.save(attributeValue);
        return new ResponseEntity<>(savedAttributeValue.getId(), HttpStatus.OK);
    }

    @PutMapping("/attributes/value/{id}")
    public ResponseEntity<?> update(@RequestBody AttributeValue attributeValue, @PathVariable Long id) {
        Optional<AttributeValue> attributeOptional = attributeValueDao.findById(id);
        if (!attributeOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
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
        if (!attribute.isPresent()) {
            throw new AttributeValueNotFoundException("BasicAttributes value not found with id -" + id);
        }
        return attribute.get();
    }

    @GetMapping("/attributes/value/category/{pid}")
    public List<AttributeValue> retrieveAllByCategory(@PathVariable Long pid) {
        return attributeValueDao.findAllByCategory_idOrderByIdAsc(pid);
    }

    @GetMapping("/attributes/value/name/{pid}")
    public List<AttributeValue> retrieveAllByAttributeName(@PathVariable Long pid) {
        return attributeValueDao.findAllByAttributeName_idOrderByIdAsc(pid);
    }

//    @PostMapping("/attributes/value/ids")
//    public List<AttributeValue> retrieveAllByIds(@RequestBody Long[] ids) {
//        Specification<AttributeValue> specification = new Specification<AttributeValue>() {
//            @Override
//            public Predicate toPredicate(Root<AttributeValue> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                List<Predicate> predicates = new ArrayList<>();
//                Path<Long> path = root.get("id");
//                CriteriaBuilder.In<Long> in = cb.in(path);
//                for(Long id : ids) {
//                    in.value(id);
//                }
//                predicates.add(in);
//                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
//            }
//        };
//        Sort sort = Sort.by(Sort.Direction.ASC,"id");
//        return attributeValueDao.findAll(specification, sort);
//    }
}
