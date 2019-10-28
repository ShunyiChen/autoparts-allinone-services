package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.ProductDao;
import com.shunyi.autoparts.exception.ProductNotFoundException;
import com.shunyi.autoparts.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/** 产品控制器 */
@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @PostMapping("/products")
    public ResponseEntity<?> create(@RequestBody Product product) {
        product.setDateCreated(new Date());
        Product savedProduct = productDao.save(product);
        return new ResponseEntity<>(savedProduct.getId(), HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> update(@RequestBody Product product, @PathVariable Long id) {
        Optional<Product> productOptional = productDao.findById(id);
        if (!productOptional.isPresent())
            return ResponseEntity.notFound().build();
        product.setId(id);
        productDao.save(product);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable Long id) {
        productDao.deleteById(id);
    }

    @GetMapping("/products")
    public List<Product> retrieveAll() {
        return productDao.findAll(Sort.by(Sort.Direction.ASC,"id"));
    }

    @GetMapping("/products/brandSeries/{bid}")
    public List<Product> retrieveAllByBrand(@PathVariable Long bid) {
        return productDao.findAllByBrandSeries_idOrderByIdAsc(bid);
    }

    @GetMapping("/products/{id}")
    public Product retrieve(@PathVariable Long id) {
        Optional<Product> product = productDao.findById(id);
        if (!product.isPresent())
            throw new ProductNotFoundException("Product not found with id -" + id);
        return product.get();
    }
}