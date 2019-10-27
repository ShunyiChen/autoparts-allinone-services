package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.CategoryDao;
import com.shunyi.autoparts.exception.CategoryNotFoundException;
import com.shunyi.autoparts.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/** 产品类目控制器 */
@RestController
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;

    @PostMapping("/categories")
    public ResponseEntity<?> create(@RequestBody Category category) {
        Category savedCategory = categoryDao.save(category);
        return new ResponseEntity<>(savedCategory.getId(), HttpStatus.OK);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<?> update(@RequestBody Category category, @PathVariable Long id) {
        Optional<Category> categoryOptional = categoryDao.findById(id);
        if (!categoryOptional.isPresent())
            return ResponseEntity.notFound().build();
        category.setId(id);
        categoryDao.save(category);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/categories/{id}")
    public void delete(@PathVariable Long id) {
        categoryDao.deleteById(id);
    }

    @GetMapping("/categories")
    public List<Category> retrieveAll() {
        return categoryDao.findAll();
    }

    @GetMapping("/categories/{id}")
    public Category retrieve(@PathVariable Long id) {
        Optional<Category> category = categoryDao.findById(id);
        if (!category.isPresent())
            throw new CategoryNotFoundException("Category not found with id -" + id);
        return category.get();
    }

    @GetMapping("/categories/sorted")
    public List<Category> retrieveAllByOrderByIdAsc() {
        return categoryDao.findAllByOrderByIdAsc();
    }
}