package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.PriceAdjustmentOrderItemDao;
import com.shunyi.autoparts.exception.PriceAdjustmentOrderItemNotFoundException;
import com.shunyi.autoparts.model.PriceAdjustmentOrderItem;
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
 * @description 调价单明细Controller
 * @author Shunyi
 * @date 2020/5/15
 */
@RestController
@CrossOrigin
public class PriceAdjustmentOrderItemController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(PriceAdjustmentOrderItemController.class);
    @Autowired
    private PriceAdjustmentOrderItemDao priceAdjustmentOrderItemDao;

    @PostMapping("/priceAdjustmentOrderItems")
    public ResponseEntity<?> create(@RequestBody PriceAdjustmentOrderItem priceAdjustmentOrderItem) {
        PriceAdjustmentOrderItem savedPurchaseOrder = priceAdjustmentOrderItemDao.save(priceAdjustmentOrderItem);
        return new ResponseEntity<>(savedPurchaseOrder.getId(), HttpStatus.OK);
    }

    @PutMapping("/priceAdjustmentOrderItems/{id}")
    public ResponseEntity<?> update(@RequestBody PriceAdjustmentOrderItem priceAdjustmentOrderItem, @PathVariable Long id) {
        Optional<PriceAdjustmentOrderItem> purchaseOrderOptional = priceAdjustmentOrderItemDao.findById(id);
        if (!purchaseOrderOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        priceAdjustmentOrderItem.setId(id);
        priceAdjustmentOrderItemDao.save(priceAdjustmentOrderItem);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/priceAdjustmentOrderItems/{id}")
    public void delete(@PathVariable Long id) {
        priceAdjustmentOrderItemDao.deleteById(id);
    }

    @DeleteMapping("/priceAdjustmentOrderItems/order/{orderId}")
    public void deleteByOrderId(@PathVariable Long orderId) {
        List<PriceAdjustmentOrderItem> itemList = priceAdjustmentOrderItemDao.findAllByPriceAdjustmentOrderIdOrderByIdAsc(orderId);
        itemList.forEach(e -> {
            priceAdjustmentOrderItemDao.deleteById(e.getId());
        });
    }

    @GetMapping("/priceAdjustmentOrderItems")
    public List<PriceAdjustmentOrderItem> retrieveAll() {
        return priceAdjustmentOrderItemDao.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @GetMapping("/priceAdjustmentOrderItems/{id}")
    public PriceAdjustmentOrderItem retrieve(@PathVariable Long id) {
        Optional<PriceAdjustmentOrderItem> priceAdjustmentOrderItem = priceAdjustmentOrderItemDao.findById(id);
        if (!priceAdjustmentOrderItem.isPresent()) {
            throw new PriceAdjustmentOrderItemNotFoundException("PriceAdjustmentOrderItem not found with id -" + id);
        }
        return priceAdjustmentOrderItem.get();
    }

    @GetMapping("/priceAdjustmentOrderItems/order/{pid}")
    public List<PriceAdjustmentOrderItem> retrieveByOrderId(@PathVariable Long pid) {
        return priceAdjustmentOrderItemDao.findAllByPriceAdjustmentOrderIdOrderByIdAsc(pid);
    }

    @PostMapping("/priceAdjustmentOrderItems/orderNo")
    public ResponseEntity<?> fetchOrderNoBySkuCode(@RequestBody PriceAdjustmentOrderItem priceAdjustmentOrderItem) {
        Specification<PriceAdjustmentOrderItem> specification = new Specification<PriceAdjustmentOrderItem>() {
            @Override
            public Predicate toPredicate(Root<PriceAdjustmentOrderItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(priceAdjustmentOrderItem != null) {
                    Path<String> path1 = root.get("sku").get("skuCode");
                    Predicate predicate1 = cb.equal(path1, priceAdjustmentOrderItem.getSku().getSkuCode());
                    predicates.add(predicate1);
                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        List<PriceAdjustmentOrderItem> orderList = priceAdjustmentOrderItemDao.findAll(specification, sort);
        if(orderList.size() > 0) {
            return new ResponseEntity<>(orderList.get(0).getPriceAdjustmentOrder().getOrderNo(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("", HttpStatus.OK);
        }
    }
}