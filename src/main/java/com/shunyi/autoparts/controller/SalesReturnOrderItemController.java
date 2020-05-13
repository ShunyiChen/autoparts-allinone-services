package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.SalesReturnOrderItemDao;
import com.shunyi.autoparts.exception.SalesReturnOrderItemNotFoundException;
import com.shunyi.autoparts.model.SalesReturnOrderItem;
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
 * @description 销售退货单明细Controller
 * @author Shunyi
 * @date 2020/5/13
 */
@RestController
@CrossOrigin
public class SalesReturnOrderItemController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(SalesReturnOrderItemController.class);
    @Autowired
    private SalesReturnOrderItemDao salesReturnOrderItemDao;

    @PostMapping("/salesReturnOrderItems")
    public ResponseEntity<?> create(@RequestBody SalesReturnOrderItem salesReturnOrderItem) {
        SalesReturnOrderItem savedPurchaseOrder = salesReturnOrderItemDao.save(salesReturnOrderItem);
        return new ResponseEntity<>(savedPurchaseOrder.getId(), HttpStatus.OK);
    }

    @PutMapping("/salesReturnOrderItems/{id}")
    public ResponseEntity<?> update(@RequestBody SalesReturnOrderItem salesReturnOrderItem, @PathVariable Long id) {
        Optional<SalesReturnOrderItem> purchaseOrderOptional = salesReturnOrderItemDao.findById(id);
        if (!purchaseOrderOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        salesReturnOrderItem.setId(id);
        salesReturnOrderItemDao.save(salesReturnOrderItem);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/salesReturnOrderItems/{id}")
    public void delete(@PathVariable Long id) {
        salesReturnOrderItemDao.deleteById(id);
    }

    @DeleteMapping("/salesReturnOrderItems/order/{orderId}")
    public void deleteByOrderId(@PathVariable Long orderId) {
        List<SalesReturnOrderItem> itemList = salesReturnOrderItemDao.findAllBySalesReturnOrderIdOrderByIdAsc(orderId);
        itemList.forEach(e -> {
            salesReturnOrderItemDao.deleteById(e.getId());
        });
    }

    @GetMapping("/salesReturnOrderItems")
    public List<SalesReturnOrderItem> retrieveAll() {
        return salesReturnOrderItemDao.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @GetMapping("/salesReturnOrderItems/{id}")
    public SalesReturnOrderItem retrieve(@PathVariable Long id) {
        Optional<SalesReturnOrderItem> salesReturnOrderItem = salesReturnOrderItemDao.findById(id);
        if (!salesReturnOrderItem.isPresent()) {
            throw new SalesReturnOrderItemNotFoundException("SalesReturnOrderItem not found with id -" + id);
        }
        return salesReturnOrderItem.get();
    }

    @GetMapping("/salesReturnOrderItems/order/{pid}")
    public List<SalesReturnOrderItem> retrieveByOrderId(@PathVariable Long pid) {
        return salesReturnOrderItemDao.findAllBySalesReturnOrderIdOrderByIdAsc(pid);
    }
}