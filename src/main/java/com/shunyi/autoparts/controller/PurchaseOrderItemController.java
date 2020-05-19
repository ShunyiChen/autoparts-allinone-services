package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.PurchaseOrderItemDao;
import com.shunyi.autoparts.exception.PurchaseOrderItemNotFoundException;
import com.shunyi.autoparts.model.PurchaseOrderItem;
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

    @DeleteMapping("/purchaseOrderItems/order/{orderId}")
    public void deleteByOrderId(@PathVariable Long orderId) {
        List<PurchaseOrderItem> itemList = purchaseOrderItemDao.findAllByPurchaseOrderIdOrderByIdAsc(orderId);
        itemList.forEach(e -> {
            purchaseOrderItemDao.deleteById(e.getId());
        });
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

    @PostMapping("/purchaseOrderItems/search")
    public List<PurchaseOrderItem> search(@RequestBody PurchaseOrderItem purchaseOrderItem) {
        Specification<PurchaseOrderItem> specification = new Specification<PurchaseOrderItem>() {
            @Override
            public Predicate toPredicate(Root<PurchaseOrderItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(purchaseOrderItem != null) {
                    Path<String> path1 = root.get("sku").get("skuCode");
                    Predicate predicate1 = cb.equal(path1, purchaseOrderItem.getSku().getSkuCode());
                    Path<String> path2 = root.get("purchaseOrder").get("supplier").get("code");
                    Predicate predicate2 = cb.equal(path2, purchaseOrderItem.getPurchaseOrder().getSupplier().getCode());
                    Path<String> path3 = root.get("purchaseOrder").get("warehouse").get("name");
                    Predicate predicate3 = cb.equal(path3, purchaseOrderItem.getPurchaseOrder().getWarehouse().getName());
                    predicates.add(predicate1);
                    predicates.add(predicate2);
                    predicates.add(predicate3);
                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return purchaseOrderItemDao.findAll(specification, sort);
    }

    @PostMapping("/purchaseOrderItems/fetchOrderNo")
    public ResponseEntity<?> fetchOrderNoBySkuCode(@RequestBody PurchaseOrderItem purchaseOrderItem) {
        List<PurchaseOrderItem> orderList = search(purchaseOrderItem);
        if(orderList.size() > 0) {
            return new ResponseEntity<>(orderList.get(0).getPurchaseOrder().getOrderNo(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("", HttpStatus.OK);
        }
    }

}