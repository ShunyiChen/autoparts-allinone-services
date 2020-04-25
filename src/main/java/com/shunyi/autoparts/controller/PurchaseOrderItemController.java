package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.PurchaseOrderItemDao;
import com.shunyi.autoparts.exception.PurchaseOrderItemNotFoundException;
import com.shunyi.autoparts.model.PurchaseOrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @description 采购订单明细Controller
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@RestController
@CrossOrigin
public class PurchaseOrderItemController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderItemController.class);
    @Autowired
    private PurchaseOrderItemDao purchaseOrderItemDao;

    @PostMapping("/purchaseOrderItems")
    public ResponseEntity<?> create(@RequestBody PurchaseOrderItem purchaseOrderItem) {
        PurchaseOrderItem savedPurchaseOrder = purchaseOrderItemDao.save(purchaseOrderItem);
        return new ResponseEntity<>(savedPurchaseOrder.getId(), HttpStatus.OK);
    }

    @PutMapping("/purchaseOrderItems/{id}")
    public ResponseEntity<?> update(@RequestBody PurchaseOrderItem purchaseOrderItem, @PathVariable Long id) {
        Optional<PurchaseOrderItem> purchaseOrderOptional = purchaseOrderItemDao.findById(id);
        if (!purchaseOrderOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        purchaseOrderItem.setId(id);
        purchaseOrderItemDao.save(purchaseOrderItem);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/purchaseOrderItems/{id}")
    public void delete(@PathVariable Long id) {
        purchaseOrderItemDao.deleteById(id);
    }

    @GetMapping("/purchaseOrderItems")
    public List<PurchaseOrderItem> retrieveAll() {
        return purchaseOrderItemDao.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @GetMapping("/purchaseOrderItems/{id}")
    public PurchaseOrderItem retrieve(@PathVariable Long id) {
        Optional<PurchaseOrderItem> purchaseOrderItem = purchaseOrderItemDao.findById(id);
        if (!purchaseOrderItem.isPresent()) {
            throw new PurchaseOrderItemNotFoundException("PurchaseOrderItem not found with id -" + id);
        }
        return purchaseOrderItem.get();
    }

    @GetMapping("/purchaseOrderItems/order/{pid}")
    public List<PurchaseOrderItem> retrieveByOrderId(@PathVariable Long pid) {
        return purchaseOrderItemDao.findAllByPurchaseOrderIdOrderByIdAsc(pid);
    }
}