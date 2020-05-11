package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.PurchaseReturnOrderDao;
import com.shunyi.autoparts.exception.PurchaseReturnOrderNotFoundException;
import com.shunyi.autoparts.model.PurchaseReturnOrder;
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
 * @description 采购退货单Controller
 * @author Shunyi
 * @date 2020/5/9
 */
@RestController
@CrossOrigin
public class PurchaseReturnOrderController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(PurchaseReturnOrderController.class);
    @Autowired
    private PurchaseReturnOrderDao purchaseReturnOrderDao;

    @PostMapping("/purchaseReturnOrders")
    public ResponseEntity<?> create(@RequestBody PurchaseReturnOrder purchaseReturnOrder) {
        purchaseReturnOrder.setDateCreated(new Date());
        PurchaseReturnOrder savedPurchaseReturnOrder = purchaseReturnOrderDao.save(purchaseReturnOrder);
        return new ResponseEntity<>(savedPurchaseReturnOrder.getId(), HttpStatus.OK);
    }

    @PutMapping("/purchaseReturnOrders/{id}")
    public ResponseEntity<?> update(@RequestBody PurchaseReturnOrder purchaseReturnOrder, @PathVariable Long id) {
        Optional<PurchaseReturnOrder> purchaseReturnOrderOptional = purchaseReturnOrderDao.findById(id);
        if (!purchaseReturnOrderOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        purchaseReturnOrder.setId(id);
        purchaseReturnOrderDao.save(purchaseReturnOrder);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/purchaseReturnOrders/{id}")
    public void delete(@PathVariable Long id) {
        purchaseReturnOrderDao.deleteById(id);
    }

    @GetMapping("/purchaseReturnOrders")
    public List<PurchaseReturnOrder> retrieveAll() {
        return purchaseReturnOrderDao.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @GetMapping("/purchaseReturnOrders/{id}")
    public PurchaseReturnOrder retrieve(@PathVariable Long id) {
        Optional<PurchaseReturnOrder> purchaseReturnOrder = purchaseReturnOrderDao.findById(id);
        if (!purchaseReturnOrder.isPresent()) {
            throw new PurchaseReturnOrderNotFoundException("PurchaseReturnOrder not found with id -" + id);
        }
        return purchaseReturnOrder.get();
    }

    @GetMapping("/purchaseReturnOrders/generation/orderNo/{userId}")
    public String generateOrderNo(@PathVariable Long userId) {
        return OrderCodeFactory.getPurchaseReturnOrderCode(userId);
    }

    @PostMapping("/purchaseReturnOrders/search")
    public List<PurchaseReturnOrder> search(@RequestBody PurchaseReturnOrder purchaseReturnOrder) {
        Specification<PurchaseReturnOrder> specification = new Specification<PurchaseReturnOrder>() {
            @Override
            public Predicate toPredicate(Root<PurchaseReturnOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(purchaseReturnOrder.getSupplier() != null && purchaseReturnOrder.getSupplier().getCode() != null) {
                    Path<String> path = root.get("supplier").get("code");
                    Predicate predicate = cb.like(path, "%"+purchaseReturnOrder.getSupplier().getCode()+"%");
                    predicates.add(predicate);
                }
                if(purchaseReturnOrder.getOrderNo() != null && !purchaseReturnOrder.getOrderNo().equals("")) {
                    Path<String> path = root.get("orderNo");
                    Predicate predicate = cb.like(path, "%"+purchaseReturnOrder.getOrderNo()+"%");
                    predicates.add(predicate);
                }
                if(purchaseReturnOrder.getOperator() != null && !purchaseReturnOrder.getOperator().equals("")) {
                    Path<String> path = root.get("operator");
                    Predicate predicate = cb.like(path, "%"+purchaseReturnOrder.getOperator()+"%");
                    predicates.add(predicate);
                }
                if(purchaseReturnOrder.getUserName() != null && !purchaseReturnOrder.getUserName().equals("")) {
                    Path<String> path = root.get("userName");
                    Predicate predicate = cb.like(path, "%"+purchaseReturnOrder.getUserName()+"%");
                    predicates.add(predicate);
                }
                if(purchaseReturnOrder.getInvoiceType() != null && !purchaseReturnOrder.getInvoiceType().equals("")) {
                    Path<String> path = root.get("invoiceType");
                    Predicate predicate = cb.like(path, "%"+purchaseReturnOrder.getInvoiceType()+"%");
                    predicates.add(predicate);
                }
                if(purchaseReturnOrder.getWarehouse() != null && purchaseReturnOrder.getWarehouse().getName() != null) {
                    Path<String> path = root.get("warehouse").get("name");
                    Predicate predicate = cb.like(path, "%"+purchaseReturnOrder.getWarehouse().getName()+"%");
                    predicates.add(predicate);
                }
                if(purchaseReturnOrder.getPayment() != null && !purchaseReturnOrder.getPayment().equals("")) {
                    Path<String> path = root.get("payment");
                    Predicate predicate = cb.like(path, "%"+purchaseReturnOrder.getPayment()+"%");
                    predicates.add(predicate);
                }
                if(purchaseReturnOrder.getNotes() != null && !purchaseReturnOrder.getNotes().equals("")) {
                    Path<String> path = root.get("notes");
                    Predicate predicate = cb.like(path, "%"+purchaseReturnOrder.getNotes()+"%");
                    predicates.add(predicate);
                }
                if(purchaseReturnOrder.getStatus() != null && !purchaseReturnOrder.getStatus().equals("")) {
                    Path<String> path = root.get("status");
                    Predicate predicate = cb.like(path, "%"+purchaseReturnOrder.getStatus()+"%");
                    predicates.add(predicate);
                }
                if(purchaseReturnOrder.getFromDate() != null && purchaseReturnOrder.getToDate() != null) {
                    Path path = root.get("orderDate");
                    Predicate predicate = cb.between(path, purchaseReturnOrder.getFromDate(), purchaseReturnOrder.getToDate());
                    predicates.add(predicate);
                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        return purchaseReturnOrderDao.findAll(specification, sort);
    }
}