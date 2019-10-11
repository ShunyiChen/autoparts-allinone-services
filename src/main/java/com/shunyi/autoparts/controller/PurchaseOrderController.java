package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.PurchaseOrderDao;
import com.shunyi.autoparts.exception.PurchaseOrderNotFoundException;
import com.shunyi.autoparts.model.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/** 进货单控制器 */
@RestController
@CrossOrigin
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderDao purchaseOrderDao;

    @PostMapping("/purchaseOrders")
    public ResponseEntity<?> create(@RequestBody PurchaseOrder purchaseOrder) {
        PurchaseOrder savedPurchaseOrder = purchaseOrderDao.save(purchaseOrder);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPurchaseOrder.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/purchaseOrders/{id}")
    public ResponseEntity<?> update(@RequestBody PurchaseOrder purchaseOrder, @PathVariable Long id) {
        Optional<PurchaseOrder> purchaseOrderOptional = purchaseOrderDao.findById(id);
        if (!purchaseOrderOptional.isPresent())
            return ResponseEntity.notFound().build();
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
        return purchaseOrderDao.findAll();
    }

    @GetMapping("/purchaseOrders/{id}")
    public PurchaseOrder retrieve(@PathVariable Long id) {
        Optional<PurchaseOrder> purchaseOrder = purchaseOrderDao.findById(id);
        if (!purchaseOrder.isPresent())
            throw new PurchaseOrderNotFoundException("PurchaseOrder not found with id -" + id);
        return purchaseOrder.get();
    }
}