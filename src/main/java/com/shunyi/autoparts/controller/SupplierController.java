package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.SupplierCategoryDao;
import com.shunyi.autoparts.dao.SupplierDao;
import com.shunyi.autoparts.exception.SupplierNotFoundException;
import com.shunyi.autoparts.model.Supplier;
import com.shunyi.autoparts.model.SupplierCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.criteria.*;
import java.net.URI;
import java.util.*;

/** 供应商控制器 */
@RestController
@CrossOrigin
public class SupplierController {
    @Autowired
    private SupplierDao supplierDao;
    @Autowired
    private SupplierCategoryDao supplierCategoryDao;

    @PostMapping("/suppliers")
    public ResponseEntity<?> create(@RequestBody Supplier supplier) {
        Supplier savedSupplier = supplierDao.save(supplier);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedSupplier.getId()).toUri();
        return ResponseEntity.created(location).build();
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
        Sort sort = new Sort(Sort.Direction.ASC,"id");
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
                if(!supplier.getPhone().equals("")) {
                    Path<String> path = root.get("phone");
                    Predicate predicate = cb.like(path, "%"+supplier.getPhone()+"%");
                    predicates.add(predicate);
                }
                if(!supplier.getOther().equals("")) {
                    Path<String> path = root.get("other");
                    Predicate predicate = cb.like(path, "%"+supplier.getOther()+"%");
                    predicates.add(predicate);
                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        Sort sort = new Sort(Sort.Direction.ASC,"id");
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
