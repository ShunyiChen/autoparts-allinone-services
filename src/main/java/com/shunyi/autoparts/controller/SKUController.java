package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.SKUDao;
import com.shunyi.autoparts.exception.SKUNotFoundException;
import com.shunyi.autoparts.model.*;
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
import java.util.*;

/**
 * @description SKU控制器
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@RestController
@CrossOrigin
public class SKUController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(SKUController.class);
    @Autowired
    private SKUDao skuDao;

    @PostMapping("/sku")
    public ResponseEntity<?> create(@RequestBody SKU sku) {
        sku.setDateCreated(new Date());
        SKU savedSKU = skuDao.save(sku);
        return new ResponseEntity<>(savedSKU.getId(), HttpStatus.OK);
    }

    @PutMapping("/sku/{id}")
    public ResponseEntity<?> update(@RequestBody SKU sku, @PathVariable Long id) {
        Optional<SKU> skuOptional = skuDao.findById(id);
        if (!skuOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        sku.setId(id);
        sku.setDateUpdated(new Date());
        sku.setUpdatedCount(sku.getUpdatedCount() == null? 1 : sku.getUpdatedCount() + 1);
        skuDao.save(sku);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/sku/{id}")
    public void delete(@PathVariable Long id) {
        skuDao.deleteById(id);
    }

    @GetMapping("/sku")
    public List<SKU> retrieveAll() {
        return skuDao.findAll();
    }

    @GetMapping("/sku/products/{pid}")
    public List<SKU> retrieveAllByProductId(@PathVariable Long pid) {
        return skuDao.findAllByProduct_idOrderByIdAsc(pid);
    }

    @GetMapping("/sku/category/{categoryId}")
    public List<SKU> retrieveAllByCategoryId(@PathVariable Long categoryId) {
        Specification<SKU> specification = new Specification<SKU>() {
            @Override
            public Predicate toPredicate(Root<SKU> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                Path<Category> path = root.get("product").get("category").get("id");
                Predicate predicate = cb.equal(path, categoryId);
                predicates.add(predicate);
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        return skuDao.findAll(specification, sort);
    }

    @GetMapping("/sku/{id}")
    public SKU retrieve(@PathVariable Long id) {
        Optional<SKU> sku = skuDao.findById(id);
        if (!sku.isPresent()) {
            throw new SKUNotFoundException("SKU not found with id -" + id);
        }
        return sku.get();
    }

    @GetMapping("/sku/code/{skuCode}")
    public SKU retrieveByCode(@PathVariable String skuCode) {
        List<SKU> list = skuDao.findAllBySkuCodeOrderByIdAsc(skuCode);
        if(list.size() > 0) {
            return list.get(0);
        }
        return new SKU();
    }

    @GetMapping("/sku/name/{skuName}")
    public SKU retrieveByName(@PathVariable String skuName) {
        List<SKU> list = skuDao.findAllBySkuNameOrderByIdAsc(skuName);
        if(list.size() > 0) {
            return list.get(0);
        }
        return new SKU();
    }

    @PostMapping("/sku/search")
    public List<SKU> search(@RequestBody SKU sku) {
        Specification<SKU> specification = new Specification<SKU>() {
            @Override
            public Predicate toPredicate(Root<SKU> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(sku.getSkuCode() != null) {
                    Path<String> path = root.get("skuCode");
                    Predicate predicate = cb.like(path, "%"+sku.getSkuCode()+"%");
                    predicates.add(predicate);
                }
                if(sku.getSkuName() != null) {
                    Path<String> path = root.get("skuName");
                    Predicate predicate = cb.like(path, "%"+sku.getSkuName()+"%");
                    predicates.add(predicate);
                }
                if(sku.getProduct().getCar() != null) {
                    Path<String> path = root.get("product").get("car").get("name");
                    Predicate predicate = cb.like(path, "%"+sku.getProduct().getCar().getName()+"%");
                    predicates.add(predicate);
                }
                if(sku.getProduct().getRelevantModels() != null) {
                    Path<String> path = root.get("product").get("relevantModels");
                    Predicate predicate = cb.like(path, "%"+sku.getProduct().getRelevantModels()+"%");
                    predicates.add(predicate);
                }
                if(sku.getProduct().getPlace() != null) {
                    Path<String> path = root.get("product").get("place").get("name");
                    Predicate predicate = cb.like(path, "%"+sku.getProduct().getPlace().getName()+"%");
                    predicates.add(predicate);
                }
                if(sku.getProduct().getSupplier() != null) {
                    Path<String> path = root.get("product").get("supplier").get("name");
                    Predicate predicate = cb.like(path, "%"+sku.getProduct().getSupplier().getName()+"%");
                    predicates.add(predicate);
                }
                if(sku.getProduct().getBrand() != null) {
                    Path<String> path = root.get("product").get("brand").get("name");
                    Predicate predicate = cb.like(path, "%"+sku.getProduct().getBrand().getName()+"%");
                    predicates.add(predicate);
                }
                if(sku.getProduct().getCompany() != null) {
                    Path<String> path = root.get("product").get("company").get("name");
                    Predicate predicate = cb.like(path, "%"+sku.getProduct().getCompany().getName()+"%");
                    predicates.add(predicate);
                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        return skuDao.findAll(specification, sort);
    }
}