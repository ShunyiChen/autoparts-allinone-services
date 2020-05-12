package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.ConsumerCategoryDao;
import com.shunyi.autoparts.exception.ConsumerCategoryNotFoundException;
import com.shunyi.autoparts.model.ConsumerCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @description 客户分类控制器
 * @author Shunyi
 * @date 2020/5/12
 */
@RestController
@CrossOrigin
public class ConsumerCategoryController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(ConsumerCategoryController.class);
    @Autowired
    private ConsumerCategoryDao consumerCategoryDao;

    @PostMapping("/consumer/categories")
    public ResponseEntity<Long> create(@RequestBody ConsumerCategory consumerCategory) {
        List<ConsumerCategory> categories = consumerCategoryDao.findAll();
        Optional<ConsumerCategory> findAny = categories.parallelStream().filter(c -> c.getName().equals(consumerCategory.getName())).findAny();
        if(!findAny.isPresent()) {
            ConsumerCategory savedCategory = consumerCategoryDao.save(consumerCategory);
            return new ResponseEntity<>(savedCategory.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>(findAny.get().getId(), HttpStatus.OK);
    }

    @PutMapping("/consumer/categories/{id}")
    public ResponseEntity<?> update(@RequestBody ConsumerCategory consumerCategory, @PathVariable Long id) {
        Optional<ConsumerCategory> categoryOptional = consumerCategoryDao.findById(id);
        if (!categoryOptional.isPresent()) {
            return ResponseEntity.notFound().build();

        }
        consumerCategory.setId(id);
        consumerCategoryDao.save(consumerCategory);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/consumer/categories/{id}")
    public void delete(@PathVariable Long id) {
        consumerCategoryDao.deleteById(id);
    }

    @GetMapping("/consumer/categories")
    public List<ConsumerCategory> retrieveAll() {
        return consumerCategoryDao.findAllByOrderByIdAsc();
    }

    @GetMapping("/consumer/categories/{id}")
    public ConsumerCategory retrieve(@PathVariable Long id) {
        Optional<ConsumerCategory> consumerCategory = consumerCategoryDao.findById(id);
        if (!consumerCategory.isPresent()) {
            throw new ConsumerCategoryNotFoundException("Consumer category not found with id -" + id);
        }
        return consumerCategory.get();
    }

    @GetMapping("/consumer/categories/root")
    public ConsumerCategory retrieveRoot() {
        return consumerCategoryDao.findByParentId(0L);
    }
}
