package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.PurchaseReturnOrderItemDao;
import com.shunyi.autoparts.exception.PurchaseReturnOrderItemNotFoundException;
import com.shunyi.autoparts.model.PurchaseReturnOrderItem;
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
 * @description 采购退货单明细Controller
 * @author Shunyi Chen
 * @date 2020/5/9
 */
@RestController
@CrossOrigin
public class PurchaseReturnOrderItemController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(PurchaseReturnOrderItemController.class);
    @Autowired
    private PurchaseReturnOrderItemDao purchaseReturnOrderItemDao;

    @PostMapping("/purchaseReturnOrderItems")
    public ResponseEntity<?> create(@RequestBody PurchaseReturnOrderItem purchaseOrderItem) {
        PurchaseReturnOrderItem savedPurchaseOrder = purchaseReturnOrderItemDao.save(purchaseOrderItem);
        return new ResponseEntity<>(savedPurchaseOrder.getId(), HttpStatus.OK);
    }

    @PutMapping("/purchaseReturnOrderItems/{id}")
    public ResponseEntity<?> update(@RequestBody PurchaseReturnOrderItem purchaseOrderItem, @PathVariable Long id) {
        Optional<PurchaseReturnOrderItem> purchaseOrderOptional = purchaseReturnOrderItemDao.findById(id);
        if (!purchaseOrderOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        purchaseOrderItem.setId(id);
        purchaseReturnOrderItemDao.save(purchaseOrderItem);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/purchaseReturnOrderItems/{id}")
    public void delete(@PathVariable Long id) {
        purchaseReturnOrderItemDao.deleteById(id);
    }

    @GetMapping("/purchaseReturnOrderItems")
    public List<PurchaseReturnOrderItem> retrieveAll() {
        return purchaseReturnOrderItemDao.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @GetMapping("/purchaseReturnOrderItems/{id}")
    public PurchaseReturnOrderItem retrieve(@PathVariable Long id) {
        Optional<PurchaseReturnOrderItem> purchaseOrderItem = purchaseReturnOrderItemDao.findById(id);
        if (!purchaseOrderItem.isPresent()) {
            throw new PurchaseReturnOrderItemNotFoundException("PurchaseReturnOrderItem not found with id -" + id);
        }
        return purchaseOrderItem.get();
    }

    @GetMapping("/purchaseReturnOrderItems/order/{pid}")
    public List<PurchaseReturnOrderItem> retrieveByOrderId(@PathVariable Long pid) {
        return purchaseReturnOrderItemDao.findAllByPurchaseReturnOrderIdOrderByIdAsc(pid);
    }
}