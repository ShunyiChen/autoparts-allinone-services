package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.SupplierCategoryDao;
import com.shunyi.autoparts.dao.SupplierDao;
import com.shunyi.autoparts.exception.SupplierNotFoundException;
import com.shunyi.autoparts.model.Supplier;
import com.shunyi.autoparts.model.SupplierCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * @description 供应商控制器
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@RestController
@CrossOrigin
public class SupplierController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(SupplierController.class);
    @Autowired
    private SupplierDao supplierDao;
    @Autowired
    private SupplierCategoryDao supplierCategoryDao;

    @PostMapping("/suppliers")
    public ResponseEntity<?> create(@RequestBody Supplier supplier) {
        Supplier savedSupplier = supplierDao.save(supplier);
        return new ResponseEntity<>(savedSupplier.getId(), HttpStatus.OK);
    }

    @PutMapping("/suppliers/{id}")
    public ResponseEntity<?> update(@RequestBody Supplier supplier, @PathVariable Long id) {
        Optional<Supplier> supplierOptional = supplierDao.findById(id);
        if (!supplierOptional.isPresent())
            return ResponseEntity.notFound().build();
        supplier.setId(id);
        supplierDao.save(supplier);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/suppliers/{id}")
    public void delete(@PathVariable Long id) {
        supplierDao.deleteById(id);
    }

    @GetMapping("/suppliers")
    public List<Supplier> retrieveAll() {
        return supplierDao.findAll();
    }

    @GetMapping("/suppliers/{id}")
    public Supplier retrieve(@PathVariable Long id) {
        Optional<Supplier> supplier = supplierDao.findById(id);
        if (!supplier.isPresent())
            throw new SupplierNotFoundException("Supplier not found with id -" + id);
        return supplier.get();
    }

    @GetMapping("/suppliers/category/{pid}")
    public List<Supplier> retrieveAll(@PathVariable Long pid) {
        List<SupplierCategory> allCategories = supplierCategoryDao.findAllByOrderByIdAsc();
        Set<Long> idSet = new HashSet<>();
        idSet.add(pid);
        getNodes(pid, allCategories, idSet);
        Specification<Supplier> specification = new Specification<Supplier>() {
            @Override
            public Predicate toPredicate(Root<Supplier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                Path<Long> path = root.get("category");
                CriteriaBuilder.In<Long> in = cb.in(path);
                idSet.stream().forEach(e -> {
                    in.value(e.longValue());
                });
                predicates.add(in);
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        return supplierDao.findAll(specification, sort);
    }

    @PostMapping("/suppliers/search")
    public List<Supplier> search(@RequestBody Supplier supplier) {
        Specification<Supplier> specification = new Specification<Supplier>() {
            @Override
            public Predicate toPredicate(Root<Supplier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(!supplier.getCode().equals("")) {
                    Path<String> path = root.get("code");
                    Predicate predicate = cb.like(path, "%"+supplier.getCode()+"%");
                    predicates.add(predicate);
                }
                if(!supplier.getName().equals("")) {
                    Path<String> path = root.get("name");
                    Predicate predicate = cb.like(path, "%"+supplier.getName()+"%");
                    predicates.add(predicate);
                }
                if(!supplier.getContact().equals("")) {
                    Path<String> path = root.get("contact");
                    Predicate predicate = cb.like(path, "%"+supplier.getContact()+"%");
                    predicates.add(predicate);
                }
                if(!supplier.getPhone1().equals("")) {
                    Path<String> path = root.get("phone1");
                    Predicate predicate = cb.like(path, "%"+supplier.getPhone1()+"%");
                    predicates.add(predicate);
                }
                if(!supplier.getPhone2().equals("")) {
                    Path<String> path = root.get("phone2");
                    Predicate predicate = cb.like(path, "%"+supplier.getPhone2()+"%");
                    predicates.add(predicate);
                }
                if(!supplier.getEmail().equals("")) {
                    Path<String> path = root.get("email");
                    Predicate predicate = cb.like(path, "%"+supplier.getEmail()+"%");
                    predicates.add(predicate);
                }
                if(!supplier.getAddress().equals("")) {
                    Path<String> path = root.get("address");
                    Predicate predicate = cb.like(path, "%"+supplier.getAddress()+"%");
                    predicates.add(predicate);
                }
                if(!supplier.getPostCode().equals("")) {
                    Path<String> path = root.get("postCode");
                    Predicate predicate = cb.like(path, "%"+supplier.getPostCode()+"%");
                    predicates.add(predicate);
                }
                if(!supplier.getPayment().equals("")) {
                    Path<String> path = root.get("payment");
                    Predicate predicate = cb.like(path, "%"+supplier.getPayment()+"%");
                    predicates.add(predicate);
                }
                if(!supplier.getGrade().equals("")) {
                    Path<String> path = root.get("grade");
                    Predicate predicate = cb.like(path, "%"+supplier.getGrade()+"%");
                    predicates.add(predicate);
                }
                if(!supplier.getNotes().equals("")) {
                    Path<String> path = root.get("notes");
                    Predicate predicate = cb.like(path, "%"+supplier.getNotes()+"%");
                    predicates.add(predicate);
                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        return supplierDao.findAll(specification, sort);
    }

    /**
     *
     * @param pid
     * @param all
     * @param idSet
     */
    private void getNodes(Long pid, List<SupplierCategory> all, Set<Long> idSet) {
        for(SupplierCategory sc : all) {
            if(sc.getParentId() == pid) {
                idSet.add(sc.getId());
                getNodes(sc.getId(), all, idSet);
            }
        }
    }

}
