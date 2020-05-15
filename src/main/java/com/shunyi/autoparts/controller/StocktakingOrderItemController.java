package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.StocktakingOrderItemDao;
import com.shunyi.autoparts.exception.StocktakingOrderItemNotFoundException;
import com.shunyi.autoparts.model.StocktakingOrderItem;
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
 * @description 盘点单明细Controller
 * @author Shunyi
 * @date 2020/5/13
 */
@RestController
@CrossOrigin
public class StocktakingOrderItemController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(StocktakingOrderItemController.class);
    @Autowired
    private StocktakingOrderItemDao stocktakingOrderItemDao;

    @PostMapping("/stocktakingOrderItems")
    public ResponseEntity<?> create(@RequestBody StocktakingOrderItem stocktakingOrderItem) {
        StocktakingOrderItem savedPurchaseOrder = stocktakingOrderItemDao.save(stocktakingOrderItem);
        return new ResponseEntity<>(savedPurchaseOrder.getId(), HttpStatus.OK);
    }

    @PutMapping("/stocktakingOrderItems/{id}")
    public ResponseEntity<?> update(@RequestBody StocktakingOrderItem stocktakingOrderItem, @PathVariable Long id) {
        Optional<StocktakingOrderItem> stocktakingOrderOptional = stocktakingOrderItemDao.findById(id);
        if (!stocktakingOrderOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        stocktakingOrderItem.setId(id);
        stocktakingOrderItemDao.save(stocktakingOrderItem);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/stocktakingOrderItems/{id}")
    public void delete(@PathVariable Long id) {
        stocktakingOrderItemDao.deleteById(id);
    }

    @DeleteMapping("/stocktakingOrderItems/order/{orderId}")
    public void deleteByOrderId(@PathVariable Long orderId) {
        List<StocktakingOrderItem> itemList = stocktakingOrderItemDao.findAllByStocktakingOrderIdOrderByIdAsc(orderId);
        itemList.forEach(e -> {
            stocktakingOrderItemDao.deleteById(e.getId());
        });
    }

    @GetMapping("/stocktakingOrderItems")
    public List<StocktakingOrderItem> retrieveAll() {
        return stocktakingOrderItemDao.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @GetMapping("/stocktakingOrderItems/{id}")
    public StocktakingOrderItem retrieve(@PathVariable Long id) {
        Optional<StocktakingOrderItem> stocktakingOrderItem = stocktakingOrderItemDao.findById(id);
        if (!stocktakingOrderItem.isPresent()) {
            throw new StocktakingOrderItemNotFoundException("StocktakingOrderItem not found with id -" + id);
        }
        return stocktakingOrderItem.get();
    }

    @GetMapping("/stocktakingOrderItems/order/{pid}")
    public List<StocktakingOrderItem> retrieveByOrderId(@PathVariable Long pid) {
        return stocktakingOrderItemDao.findAllByStocktakingOrderIdOrderByIdAsc(pid);
    }
}