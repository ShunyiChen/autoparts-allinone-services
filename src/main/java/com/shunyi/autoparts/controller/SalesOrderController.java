package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.SalesOrderDao;
import com.shunyi.autoparts.exception.SalesOrderNotFoundException;
import com.shunyi.autoparts.model.SalesOrder;
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
 * @description 销售单控制器
 * @author Shunyi
 * @date 2020/5/12
 */
@RestController
@CrossOrigin
public class SalesOrderController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(SalesOrderController.class);
    @Autowired
    private SalesOrderDao salesOrderDao;

    @PostMapping("/salesOrders")
    public ResponseEntity<?> create(@RequestBody SalesOrder salesOrder) {
        salesOrder.setDateCreated(new Date());
        SalesOrder savedSalesOrder = salesOrderDao.save(salesOrder);
        return new ResponseEntity<>(savedSalesOrder.getId(), HttpStatus.OK);
    }

    @PutMapping("/salesOrders/{id}")
    public ResponseEntity<?> update(@RequestBody SalesOrder salesOrder, @PathVariable Long id) {
        Optional<SalesOrder> salesOrderOptional = salesOrderDao.findById(id);
        if (!salesOrderOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        salesOrder.setId(id);
        salesOrderDao.save(salesOrder);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/salesOrders/{id}")
    public void delete(@PathVariable Long id) {
        salesOrderDao.deleteById(id);
    }

    @GetMapping("/salesOrders")
    public List<SalesOrder> retrieveAll() {
        return salesOrderDao.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @GetMapping("/salesOrders/{id}")
    public SalesOrder retrieve(@PathVariable Long id) {
        Optional<SalesOrder> salesOrder = salesOrderDao.findById(id);
        if (!salesOrder.isPresent()) {
            throw new SalesOrderNotFoundException("SalesOrder not found with id -" + id);
        }
        return salesOrder.get();
    }

    @GetMapping("/salesOrders/orderNo/{orderNo}")
    public SalesOrder retrieve(@PathVariable String orderNo) {
        List<SalesOrder> salesOrderList = salesOrderDao.findAllByOrderNoOrderByIdAsc(orderNo);
        if (salesOrderList.size() > 0) {
            return salesOrderList.get(0);
        }
        return null;
    }

    @GetMapping("/salesOrders/generation/orderNo/{userId}")
    public String generateOrderNo(@PathVariable Long userId) {
        return OrderCodeFactory.getSalesOrderCode(userId);
    }

    @PostMapping("/salesOrders/search")
    public List<SalesOrder> search(@RequestBody SalesOrder salesOrder) {
        Specification<SalesOrder> specification = new Specification<SalesOrder>() {
            @Override
            public Predicate toPredicate(Root<SalesOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(salesOrder.getConsumer() != null && salesOrder.getConsumer().getCode() != null) {
                    Path<String> path = root.get("consumer").get("code");
                    Predicate predicate = cb.like(path, "%"+salesOrder.getConsumer().getCode()+"%");
                    predicates.add(predicate);
                }
                if(salesOrder.getOrderNo() != null && !salesOrder.getOrderNo().equals("")) {
                    Path<String> path = root.get("orderNo");
                    Predicate predicate = cb.like(path, "%"+salesOrder.getOrderNo()+"%");
                    predicates.add(predicate);
                }
                if(salesOrder.getOperator() != null && !salesOrder.getOperator().equals("")) {
                    Path<String> path = root.get("operator");
                    Predicate predicate = cb.like(path, "%"+salesOrder.getOperator()+"%");
                    predicates.add(predicate);
                }
                if(salesOrder.getUserName() != null && !salesOrder.getUserName().equals("")) {
                    Path<String> path = root.get("userName");
                    Predicate predicate = cb.like(path, "%"+salesOrder.getUserName()+"%");
                    predicates.add(predicate);
                }
                if(salesOrder.getInvoiceType() != null && !salesOrder.getInvoiceType().equals("")) {
                    Path<String> path = root.get("invoiceType");
                    Predicate predicate = cb.like(path, "%"+salesOrder.getInvoiceType()+"%");
                    predicates.add(predicate);
                }
                if(salesOrder.getWarehouse() != null && salesOrder.getWarehouse().getName() != null) {
                    Path<String> path = root.get("warehouse").get("name");
                    Predicate predicate = cb.like(path, "%"+salesOrder.getWarehouse().getName()+"%");
                    predicates.add(predicate);
                }
                if(salesOrder.getPayment() != null && !salesOrder.getPayment().equals("")) {
                    Path<String> path = root.get("payment");
                    Predicate predicate = cb.like(path, "%"+salesOrder.getPayment()+"%");
                    predicates.add(predicate);
                }
                if(salesOrder.getNotes() != null && !salesOrder.getNotes().equals("")) {
                    Path<String> path = root.get("notes");
                    Predicate predicate = cb.like(path, "%"+salesOrder.getNotes()+"%");
                    predicates.add(predicate);
                }
                if(salesOrder.getStatus() != null && !salesOrder.getStatus().equals("")) {
                    Path<String> path = root.get("status");
                    Predicate predicate = cb.like(path, "%"+salesOrder.getStatus()+"%");
                    predicates.add(predicate);
                }
                if(salesOrder.getFromDate() != null && salesOrder.getToDate() != null) {
                    if(salesOrder.getDateType().equals("单据日期")) {
                        Path path = root.get("orderDate");
                        Predicate predicate = cb.between(path, salesOrder.getFromDate(), salesOrder.getToDate());
                        predicates.add(predicate);
                    } else {
                        Path path = root.get("repaymentDate");
                        Predicate predicate = cb.between(path, salesOrder.getFromDate(), salesOrder.getToDate());
                        predicates.add(predicate);
                    }
                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        return salesOrderDao.findAll(specification, sort);
    }
}