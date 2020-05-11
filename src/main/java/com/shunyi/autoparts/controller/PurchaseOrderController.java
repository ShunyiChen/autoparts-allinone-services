package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.PurchaseOrderDao;
import com.shunyi.autoparts.exception.PurchaseOrderNotFoundException;
import com.shunyi.autoparts.model.PurchaseOrder;
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
 * @description 采购订单控制器
 * @author Shunyi Chen
 * @date 2020/4/25
 */
@RestController
@CrossOrigin
public class PurchaseOrderController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);
    @Autowired
    private PurchaseOrderDao purchaseOrderDao;

    @PostMapping("/purchaseOrders")
    public ResponseEntity<?> create(@RequestBody PurchaseOrder purchaseOrder) {
        purchaseOrder.setDateCreated(new Date());
        PurchaseOrder savedPurchaseOrder = purchaseOrderDao.save(purchaseOrder);
        return new ResponseEntity<>(savedPurchaseOrder.getId(), HttpStatus.OK);
    }

    @PutMapping("/purchaseOrders/{id}")
    public ResponseEntity<?> update(@RequestBody PurchaseOrder purchaseOrder, @PathVariable Long id) {
        Optional<PurchaseOrder> purchaseOrderOptional = purchaseOrderDao.findById(id);
        if (!purchaseOrderOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        purchaseOrder.setId(id);
        purchaseOrderDao.save(purchaseOrder);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/purchaseOrders/{id}")
    public void delete(@PathVariable Long id) {
        purchaseOrderDao.deleteById(id);
    }

    @GetMapping("/purchaseOrders")
    public List<PurchaseOrder> retrieveAll() {
        return purchaseOrderDao.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @GetMapping("/purchaseOrders/{id}")
    public PurchaseOrder retrieve(@PathVariable Long id) {
        Optional<PurchaseOrder> purchaseOrder = purchaseOrderDao.findById(id);
        if (!purchaseOrder.isPresent()) {
            throw new PurchaseOrderNotFoundException("PurchaseOrder not found with id -" + id);
        }
        return purchaseOrder.get();
    }

    @GetMapping("/purchaseOrders/orderNo/{orderNo}")
    public PurchaseOrder retrieve(@PathVariable String orderNo) {
        List<PurchaseOrder> purchaseOrderList = purchaseOrderDao.findAllByOrderNoOrderByIdAsc(orderNo);
        if (purchaseOrderList.size() > 0) {
            return purchaseOrderList.get(0);
        }
        return null;
    }

    @GetMapping("/purchaseOrders/generation/orderNo/{userId}")
    public String generateOrderNo(@PathVariable Long userId) {
        return OrderCodeFactory.getPurchaseOrderCode(userId);
    }

    @PostMapping("/purchaseOrders/search")
    public List<PurchaseOrder> search(@RequestBody PurchaseOrder purchaseOrder) {
        Specification<PurchaseOrder> specification = new Specification<PurchaseOrder>() {
            @Override
            public Predicate toPredicate(Root<PurchaseOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(purchaseOrder.getSupplier() != null && purchaseOrder.getSupplier().getCode() != null) {
                    Path<String> path = root.get("supplier").get("code");
                    Predicate predicate = cb.like(path, "%"+purchaseOrder.getSupplier().getCode()+"%");
                    predicates.add(predicate);
                }
                if(purchaseOrder.getOrderNo() != null && !purchaseOrder.getOrderNo().equals("")) {
                    Path<String> path = root.get("orderNo");
                    Predicate predicate = cb.like(path, "%"+purchaseOrder.getOrderNo()+"%");
                    predicates.add(predicate);
                }
                if(purchaseOrder.getOperator() != null && !purchaseOrder.getOperator().equals("")) {
                    Path<String> path = root.get("operator");
                    Predicate predicate = cb.like(path, "%"+purchaseOrder.getOperator()+"%");
                    predicates.add(predicate);
                }
                if(purchaseOrder.getUserName() != null && !purchaseOrder.getUserName().equals("")) {
                    Path<String> path = root.get("userName");
                    Predicate predicate = cb.like(path, "%"+purchaseOrder.getUserName()+"%");
                    predicates.add(predicate);
                }
                if(purchaseOrder.getInvoiceType() != null && !purchaseOrder.getInvoiceType().equals("")) {
                    Path<String> path = root.get("invoiceType");
                    Predicate predicate = cb.like(path, "%"+purchaseOrder.getInvoiceType()+"%");
                    predicates.add(predicate);
                }
                if(purchaseOrder.getWarehouse() != null && purchaseOrder.getWarehouse().getName() != null) {
                    Path<String> path = root.get("warehouse").get("name");
                    Predicate predicate = cb.like(path, "%"+purchaseOrder.getWarehouse().getName()+"%");
                    predicates.add(predicate);
                }
                if(purchaseOrder.getPayment() != null && !purchaseOrder.getPayment().equals("")) {
                    Path<String> path = root.get("payment");
                    Predicate predicate = cb.like(path, "%"+purchaseOrder.getPayment()+"%");
                    predicates.add(predicate);
                }
                if(purchaseOrder.getNotes() != null && !purchaseOrder.getNotes().equals("")) {
                    Path<String> path = root.get("notes");
                    Predicate predicate = cb.like(path, "%"+purchaseOrder.getNotes()+"%");
                    predicates.add(predicate);
                }
                if(purchaseOrder.getStatus() != null && !purchaseOrder.getStatus().equals("")) {
                    Path<String> path = root.get("status");
                    Predicate predicate = cb.like(path, "%"+purchaseOrder.getStatus()+"%");
                    predicates.add(predicate);
                }
                if(purchaseOrder.getFromDate() != null && purchaseOrder.getToDate() != null) {
                    if(purchaseOrder.getDateType().equals("单据日期")) {
                        Path path = root.get("orderDate");
                        Predicate predicate = cb.between(path, purchaseOrder.getFromDate(), purchaseOrder.getToDate());
                        predicates.add(predicate);
                    } else {
                        Path path = root.get("repaymentDate");
                        Predicate predicate = cb.between(path, purchaseOrder.getFromDate(), purchaseOrder.getToDate());
                        predicates.add(predicate);
                    }
                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        return purchaseOrderDao.findAll(specification, sort);
    }
}