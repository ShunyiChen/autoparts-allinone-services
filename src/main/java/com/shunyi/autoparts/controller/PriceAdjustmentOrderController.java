package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.PriceAdjustmentOrderDao;
import com.shunyi.autoparts.exception.PriceAdjustmentOrderNotFoundException;
import com.shunyi.autoparts.model.PriceAdjustmentOrder;
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
import java.util.List;
import java.util.Optional;

/**
 * @description 调价单Controller
 * @author Shunyi
 * @date 2020/5/15
 */
@RestController
@CrossOrigin
public class PriceAdjustmentOrderController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(PriceAdjustmentOrderController.class);
    @Autowired
    private PriceAdjustmentOrderDao priceAdjustmentOrderDao;

    @PostMapping("/priceAdjustmentOrders")
    public ResponseEntity<?> create(@RequestBody PriceAdjustmentOrder priceAdjustmentOrder) {
        PriceAdjustmentOrder savedPriceAdjustmentOrder = priceAdjustmentOrderDao.save(priceAdjustmentOrder);
        return new ResponseEntity<>(savedPriceAdjustmentOrder.getId(), HttpStatus.OK);
    }

    @PutMapping("/priceAdjustmentOrders/{id}")
    public ResponseEntity<?> update(@RequestBody PriceAdjustmentOrder priceAdjustmentOrder, @PathVariable Long id) {
        Optional<PriceAdjustmentOrder> priceAdjustmentOrderOptional = priceAdjustmentOrderDao.findById(id);
        if (!priceAdjustmentOrderOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        priceAdjustmentOrder.setId(id);
        priceAdjustmentOrderDao.save(priceAdjustmentOrder);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/priceAdjustmentOrders/{id}")
    public void delete(@PathVariable Long id) {
        priceAdjustmentOrderDao.deleteById(id);
    }

    @GetMapping("/priceAdjustmentOrders")
    public List<PriceAdjustmentOrder> retrieveAll() {
        return priceAdjustmentOrderDao.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @GetMapping("/priceAdjustmentOrders/{id}")
    public PriceAdjustmentOrder retrieve(@PathVariable Long id) {
        Optional<PriceAdjustmentOrder> priceAdjustmentOrder = priceAdjustmentOrderDao.findById(id);
        if (!priceAdjustmentOrder.isPresent()) {
            throw new PriceAdjustmentOrderNotFoundException("PriceAdjustmentOrder not found with id -" + id);
        }
        return priceAdjustmentOrder.get();
    }

    @GetMapping("/priceAdjustmentOrders/orderNo/{orderNo}")
    public PriceAdjustmentOrder retrieve(@PathVariable String orderNo) {
        List<PriceAdjustmentOrder> priceAdjustmentOrderList = priceAdjustmentOrderDao.findAllByOrderNoOrderByIdAsc(orderNo);
        if (priceAdjustmentOrderList.size() > 0) {
            return priceAdjustmentOrderList.get(0);
        }
        return null;
    }

    @GetMapping("/priceAdjustmentOrders/generation/orderNo/{userId}")
    public String generateOrderNo(@PathVariable Long userId) {
        return OrderCodeFactory.getPriceAdjustmentOrderCode(userId);
    }

    @PostMapping("/priceAdjustmentOrders/search")
    public List<PriceAdjustmentOrder> search(@RequestBody PriceAdjustmentOrder priceAdjustmentOrder) {
        Specification<PriceAdjustmentOrder> specification = new Specification<PriceAdjustmentOrder>() {
            @Override
            public Predicate toPredicate(Root<PriceAdjustmentOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(priceAdjustmentOrder.getOrderNo() != null && !priceAdjustmentOrder.getOrderNo().equals("")) {
                    Path<String> path = root.get("orderNo");
                    Predicate predicate = cb.like(path, "%"+priceAdjustmentOrder.getOrderNo()+"%");
                    predicates.add(predicate);
                }
                if(priceAdjustmentOrder.getOperator() != null && !priceAdjustmentOrder.getOperator().equals("")) {
                    Path<String> path = root.get("operator");
                    Predicate predicate = cb.like(path, "%"+priceAdjustmentOrder.getOperator()+"%");
                    predicates.add(predicate);
                }
                if(priceAdjustmentOrder.getUserName() != null && !priceAdjustmentOrder.getUserName().equals("")) {
                    Path<String> path = root.get("userName");
                    Predicate predicate = cb.like(path, "%"+priceAdjustmentOrder.getUserName()+"%");
                    predicates.add(predicate);
                }
                if(priceAdjustmentOrder.getWarehouse() != null && priceAdjustmentOrder.getWarehouse().getName() != null) {
                    Path<String> path = root.get("warehouse").get("name");
                    Predicate predicate = cb.like(path, "%"+priceAdjustmentOrder.getWarehouse().getName()+"%");
                    predicates.add(predicate);
                }
                if(priceAdjustmentOrder.getNotes() != null && !priceAdjustmentOrder.getNotes().equals("")) {
                    Path<String> path = root.get("notes");
                    Predicate predicate = cb.like(path, "%"+priceAdjustmentOrder.getNotes()+"%");
                    predicates.add(predicate);
                }
                if(priceAdjustmentOrder.getStatus() != null && !priceAdjustmentOrder.getStatus().equals("")) {
                    Path<String> path = root.get("status");
                    Predicate predicate = cb.equal(path, priceAdjustmentOrder.getStatus());
                    predicates.add(predicate);
                }
                if(priceAdjustmentOrder.getFromDate() != null && priceAdjustmentOrder.getToDate() != null) {
                    Path path = root.get("orderDate");
                    Predicate predicate = cb.between(path, priceAdjustmentOrder.getFromDate(), priceAdjustmentOrder.getToDate());
                    predicates.add(predicate);
                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        return priceAdjustmentOrderDao.findAll(specification, sort);
    }
}