package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.SalesReturnOrderDao;
import com.shunyi.autoparts.exception.SalesReturnOrderNotFoundException;
import com.shunyi.autoparts.model.SalesReturnOrder;
import com.shunyi.autoparts.service.OrderCodeFactory;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @description 销售退货单Controller
 * @author Shunyi
 * @date 2020/5/13
 */
@RestController
@CrossOrigin
public class SalesReturnOrderController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(SalesReturnOrderController.class);
    @Autowired
    private SalesReturnOrderDao salesReturnOrderDao;

    @PostMapping("/salesReturnOrders")
    public ResponseEntity<?> create(@RequestBody SalesReturnOrder salesReturnOrder) {
        salesReturnOrder.setDateCreated(new Date());
        SalesReturnOrder savedSalesReturnOrder = salesReturnOrderDao.save(salesReturnOrder);
        return new ResponseEntity<>(savedSalesReturnOrder.getId(), HttpStatus.OK);
    }

    @PutMapping("/salesReturnOrders/{id}")
    public ResponseEntity<?> update(@RequestBody SalesReturnOrder salesReturnOrder, @PathVariable Long id) {
        Optional<SalesReturnOrder> salesReturnOrderOptional = salesReturnOrderDao.findById(id);
        if (!salesReturnOrderOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        salesReturnOrder.setId(id);
        salesReturnOrderDao.save(salesReturnOrder);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/salesReturnOrders/{id}")
    public void delete(@PathVariable Long id) {
        salesReturnOrderDao.deleteById(id);
    }

    @GetMapping("/salesReturnOrders")
    public List<SalesReturnOrder> retrieveAll() {
        return salesReturnOrderDao.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @GetMapping("/salesReturnOrders/{id}")
    public SalesReturnOrder retrieve(@PathVariable Long id) {
        Optional<SalesReturnOrder> salesReturnOrder = salesReturnOrderDao.findById(id);
        if (!salesReturnOrder.isPresent()) {
            throw new SalesReturnOrderNotFoundException("SalesReturnOrder not found with id -" + id);
        }
        return salesReturnOrder.get();
    }

    @GetMapping("/salesReturnOrders/generation/orderNo/{userId}")
    public String generateOrderNo(@PathVariable Long userId) {
        return OrderCodeFactory.getSalesReturnOrderCode(userId);
    }

    @PostMapping("/salesReturnOrders/search")
    public List<SalesReturnOrder> search(@RequestBody SalesReturnOrder salesReturnOrder) {
        Specification<SalesReturnOrder> specification = new Specification<SalesReturnOrder>() {
            @Override
            public Predicate toPredicate(Root<SalesReturnOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(salesReturnOrder.getConsumer() != null && salesReturnOrder.getConsumer().getCode() != null) {
                    Path<String> path = root.get("consumer").get("code");
                    Predicate predicate = cb.like(path, "%"+salesReturnOrder.getConsumer().getCode()+"%");
                    predicates.add(predicate);
                }
                if(salesReturnOrder.getOrderNo() != null && !salesReturnOrder.getOrderNo().equals("")) {
                    Path<String> path = root.get("orderNo");
                    Predicate predicate = cb.like(path, "%"+salesReturnOrder.getOrderNo()+"%");
                    predicates.add(predicate);
                }
                if(salesReturnOrder.getOperator() != null && !salesReturnOrder.getOperator().equals("")) {
                    Path<String> path = root.get("operator");
                    Predicate predicate = cb.like(path, "%"+salesReturnOrder.getOperator()+"%");
                    predicates.add(predicate);
                }
                if(salesReturnOrder.getUserName() != null && !salesReturnOrder.getUserName().equals("")) {
                    Path<String> path = root.get("userName");
                    Predicate predicate = cb.like(path, "%"+salesReturnOrder.getUserName()+"%");
                    predicates.add(predicate);
                }
                if(salesReturnOrder.getInvoiceType() != null && !salesReturnOrder.getInvoiceType().equals("")) {
                    Path<String> path = root.get("invoiceType");
                    Predicate predicate = cb.like(path, "%"+salesReturnOrder.getInvoiceType()+"%");
                    predicates.add(predicate);
                }
                if(salesReturnOrder.getWarehouse() != null && salesReturnOrder.getWarehouse().getName() != null) {
                    Path<String> path = root.get("warehouse").get("name");
                    Predicate predicate = cb.like(path, "%"+salesReturnOrder.getWarehouse().getName()+"%");
                    predicates.add(predicate);
                }
                if(salesReturnOrder.getPayment() != null && !salesReturnOrder.getPayment().equals("")) {
                    Path<String> path = root.get("payment");
                    Predicate predicate = cb.like(path, "%"+salesReturnOrder.getPayment()+"%");
                    predicates.add(predicate);
                }
                if(salesReturnOrder.getNotes() != null && !salesReturnOrder.getNotes().equals("")) {
                    Path<String> path = root.get("notes");
                    Predicate predicate = cb.like(path, "%"+salesReturnOrder.getNotes()+"%");
                    predicates.add(predicate);
                }
                if(salesReturnOrder.getStatus() != null && !salesReturnOrder.getStatus().equals("")) {
                    Path<String> path = root.get("status");
                    Predicate predicate = cb.like(path, "%"+salesReturnOrder.getStatus()+"%");
                    predicates.add(predicate);
                }
                if(salesReturnOrder.getFromDate() != null && salesReturnOrder.getToDate() != null) {
                    Path path = root.get("orderDate");
                    Predicate predicate = cb.between(path, salesReturnOrder.getFromDate(), salesReturnOrder.getToDate());
                    predicates.add(predicate);
                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        return salesReturnOrderDao.findAll(specification, sort);
    }
}