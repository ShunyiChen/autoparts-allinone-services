package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.ProductDao;
import com.shunyi.autoparts.exception.ProductNotFoundException;
import com.shunyi.autoparts.model.BrandSeries;
import com.shunyi.autoparts.model.Car;
import com.shunyi.autoparts.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/** 产品控制器 */
@RestController
@CrossOrigin
public class ProductController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
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

    @PostMapping("/products/search")
    public List<Product> search(@RequestBody Product product) {
        Specification<Product> specification = new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(!product.getCode().equals("")) {
                    Path<String> path = root.get("code");
                    Predicate predicate = cb.like(path, "%"+product.getCode()+"%");
                    predicates.add(predicate);
                }
                if(!product.getName().equals("")) {
                    Path<String> path = root.get("name");
                    Predicate predicate = cb.like(path, "%"+product.getName()+"%");
                    predicates.add(predicate);
                }
                if(!product.getBrandSeries().equals("")) {
                    Path<BrandSeries> path = root.get("brandSeries");
                    Predicate predicate = cb.like(path.get("chineseName"), "%"+product.getBrandSeries().getChineseName()+"%");
                    predicates.add(predicate);
                }
                if(product.getPriceExcludingTax() != null && !product.getPriceExcludingTax().equals("")) {
                    Path<BigDecimal> path = root.get("priceExcludingTax");
                    Predicate predicate = cb.equal(path,product.getPriceExcludingTax());
                    predicates.add(predicate);
                }
                if(!product.getUnit().equals("")) {
                    Path<String> path = root.get("unit");
                    Predicate predicate = cb.like(path, "%"+product.getUnit()+"%");
                    predicates.add(predicate);
                }
                if(!product.getImported().equals("")) {
                    Path<String> path = root.get("imported");
                    Predicate predicate = cb.like(path, "%"+product.getImported()+"%");
                    predicates.add(predicate);
                }
                if(!product.getPlaceOfOrigin().equals("")) {
                    Path<String> path = root.get("placeOfOrigin");
                    Predicate predicate = cb.like(path, "%"+product.getPlaceOfOrigin()+"%");
                    predicates.add(predicate);
                }
                if(!product.getCar().equals("")) {
                    Path<Car> path = root.get("car");
                    Predicate predicate = cb.like(path.get("model"), "%"+product.getCar().getModel()+"%");
                    predicates.add(predicate);
                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        return productDao.findAll(specification, sort);
    }
}