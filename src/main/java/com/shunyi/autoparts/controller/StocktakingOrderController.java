package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.StocktakingOrderDao;
import com.shunyi.autoparts.exception.StocktakingOrderNotFoundException;
import com.shunyi.autoparts.model.StocktakingOrder;
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
 * @description 盘点单Controller
 * @author Shunyi Chen
 * @date 2020/5/14
 */
@RestController
@CrossOrigin
public class StocktakingOrderController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(StocktakingOrderController.class);
    @Autowired
    private StocktakingOrderDao stocktakingOrderDao;

    @PostMapping("/stocktakingOrders")
    public ResponseEntity<?> create(@RequestBody StocktakingOrder stocktakingOrder) {
        StocktakingOrder savedStocktakingOrder = stocktakingOrderDao.save(stocktakingOrder);
        return new ResponseEntity<>(savedStocktakingOrder.getId(), HttpStatus.OK);
    }

    @PutMapping("/stocktakingOrders/{id}")
    public ResponseEntity<?> update(@RequestBody StocktakingOrder stocktakingOrder, @PathVariable Long id) {
        Optional<StocktakingOrder> stocktakingOrderOptional = stocktakingOrderDao.findById(id);
        if (!stocktakingOrderOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        stocktakingOrder.setId(id);
        stocktakingOrderDao.save(stocktakingOrder);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/stocktakingOrders/{id}")
    public void delete(@PathVariable Long id) {
        stocktakingOrderDao.deleteById(id);
    }

    @GetMapping("/stocktakingOrders")
    public List<StocktakingOrder> retrieveAll() {
        return stocktakingOrderDao.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @GetMapping("/stocktakingOrders/{id}")
    public StocktakingOrder retrieve(@PathVariable Long id) {
        Optional<StocktakingOrder> stocktakingOrder = stocktakingOrderDao.findById(id);
        if (!stocktakingOrder.isPresent()) {
            throw new StocktakingOrderNotFoundException("StocktakingOrder not found with id -" + id);
        }
        return stocktakingOrder.get();
    }

    @GetMapping("/stocktakingOrders/generation/orderNo/{userId}")
    public String generateOrderNo(@PathVariable Long userId) {
        return OrderCodeFactory.getStocktakingOrderCode(userId);
    }

    @PostMapping("/stocktakingOrders/search")
    public List<StocktakingOrder> search(@RequestBody StocktakingOrder stocktakingOrder) {
        Specification<StocktakingOrder> specification = new Specification<StocktakingOrder>() {
            @Override
            public Predicate toPredicate(Root<StocktakingOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(stocktakingOrder.getOrderNo() != null && !stocktakingOrder.getOrderNo().equals("")) {
                    Path<String> path = root.get("orderNo");
                    Predicate predicate = cb.like(path, "%"+stocktakingOrder.getOrderNo()+"%");
                    predicates.add(predicate);
                }
                if(stocktakingOrder.getOperator() != null && !stocktakingOrder.getOperator().equals("")) {
                    Path<String> path = root.get("operator");
                    Predicate predicate = cb.like(path, "%"+stocktakingOrder.getOperator()+"%");
                    predicates.add(predicate);
                }
                if(stocktakingOrder.getUserName() != null && !stocktakingOrder.getUserName().equals("")) {
                    Path<String> path = root.get("userName");
                    Predicate predicate = cb.like(path, "%"+stocktakingOrder.getUserName()+"%");
                    predicates.add(predicate);
                }
                if(stocktakingOrder.getWarehouse() != null && stocktakingOrder.getWarehouse().getName() != null) {
                    Path<String> path = root.get("warehouse").get("name");
                    Predicate predicate = cb.like(path, "%"+stocktakingOrder.getWarehouse().getName()+"%");
                    predicates.add(predicate);
                }
                if(stocktakingOrder.getNotes() != null && !stocktakingOrder.getNotes().equals("")) {
                    Path<String> path = root.get("notes");
                    Predicate predicate = cb.like(path, "%"+stocktakingOrder.getNotes()+"%");
                    predicates.add(predicate);
                }
                if(stocktakingOrder.getStatus() != null && !stocktakingOrder.getStatus().equals("")) {
                    Path<String> path = root.get("status");
                    Predicate predicate = cb.like(path, "%"+stocktakingOrder.getStatus()+"%");
                    predicates.add(predicate);
                }
                if(stocktakingOrder.getFromDate() != null && stocktakingOrder.getToDate() != null) {
                    Path path = root.get("orderDate");
                    Predicate predicate = cb.between(path, stocktakingOrder.getFromDate(), stocktakingOrder.getToDate());
                    predicates.add(predicate);
                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        return stocktakingOrderDao.findAll(specification, sort);
    }
}