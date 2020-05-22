package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.SalesOrderItemDao;
import com.shunyi.autoparts.exception.SalesOrderItemNotFoundException;
import com.shunyi.autoparts.model.SalesOrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @description 销售单明细Controller
 * @author Shunyi
 * @date 2020/5/12
 */
@RestController
@CrossOrigin
public class SalesOrderItemController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(SalesOrderItemController.class);
    @Autowired
    private SalesOrderItemDao salesOrderItemDao;

    @PostMapping("/salesOrderItems")
    public ResponseEntity<?> create(@RequestBody SalesOrderItem salesOrderItem) {
        SalesOrderItem savedSalesOrder = salesOrderItemDao.save(salesOrderItem);
        return new ResponseEntity<>(savedSalesOrder.getId(), HttpStatus.OK);
    }

    @PutMapping("/salesOrderItems/{id}")
    public ResponseEntity<?> update(@RequestBody SalesOrderItem salesOrderItem, @PathVariable Long id) {
        Optional<SalesOrderItem> salesOrderOptional = salesOrderItemDao.findById(id);
        if (!salesOrderOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        salesOrderItem.setId(id);
        salesOrderItemDao.save(salesOrderItem);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/salesOrderItems/{id}")
    public void delete(@PathVariable Long id) {
        salesOrderItemDao.deleteById(id);
    }

    @DeleteMapping("/salesOrderItems/order/{orderId}")
    public void deleteByOrderId(@PathVariable Long orderId) {
        List<SalesOrderItem> itemList = salesOrderItemDao.findAllBySalesOrderIdOrderByIdAsc(orderId);
        itemList.forEach(e -> {
            salesOrderItemDao.deleteById(e.getId());
        });
    }

    @GetMapping("/salesOrderItems")
    public List<SalesOrderItem> retrieveAll() {
        return salesOrderItemDao.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @GetMapping("/salesOrderItems/{id}")
    public SalesOrderItem retrieve(@PathVariable Long id) {
        Optional<SalesOrderItem> salesOrderItem = salesOrderItemDao.findById(id);
        if (!salesOrderItem.isPresent()) {
            throw new SalesOrderItemNotFoundException("SalesOrderItem not found with id -" + id);
        }
        return salesOrderItem.get();
    }

    @GetMapping("/salesOrderItems/order/{pid}")
    public List<SalesOrderItem> retrieveByOrderId(@PathVariable Long pid) {
        return salesOrderItemDao.findAllBySalesOrderIdOrderByIdAsc(pid);
    }

    @PostMapping("/salesOrderItems/search")
    public List<SalesOrderItem> search(@RequestBody SalesOrderItem salesOrderItem) {
        Specification<SalesOrderItem> specification = new Specification<SalesOrderItem>() {
            @Override
            public Predicate toPredicate(Root<SalesOrderItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(salesOrderItem != null) {
                    Path<String> path1 = root.get("sku").get("skuCode");
                    Predicate predicate1 = cb.equal(path1, salesOrderItem.getSku().getSkuCode());
                    predicates.add(predicate1);
                    Path<String> path2 = root.get("salesOrder").get("consumer").get("code");
                    Predicate predicate2 = cb.equal(path2, salesOrderItem.getSalesOrder().getConsumer().getCode());
                    predicates.add(predicate2);
                    Path<String> path3 = root.get("salesOrder").get("warehouse").get("name");
                    Predicate predicate3 = cb.equal(path3, salesOrderItem.getSalesOrder().getWarehouse().getName());
                    predicates.add(predicate3);
                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return salesOrderItemDao.findAll(specification, sort);
    }

    @PostMapping("/salesOrderItems/lastItem")
    public ResponseEntity<?> fetchOrderNoBySkuCode(@RequestBody SalesOrderItem salesOrderItem) {
        List<SalesOrderItem> orderList = search(salesOrderItem);
        if(orderList.size() > 0) {
            return new ResponseEntity<>(orderList.get(0), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new SalesOrderItem(), HttpStatus.OK);
        }
    }
}