package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.DeliveryDao;
import com.shunyi.autoparts.exception.DeliveryNotFoundException;
import com.shunyi.autoparts.model.Delivery;
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
 * @description 发货方式控制器
 * @author Shunyi
 * @date 2020/5/12
 */
@RestController
@CrossOrigin
public class DeliveryController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(DeliveryController.class);
    @Autowired
    private DeliveryDao deliveryDao;

    @PostMapping("/deliveries")
    public ResponseEntity<?> create(@RequestBody Delivery delivery) {
        List<Delivery> deliveries = deliveryDao.findAll();
        Optional<Delivery> findAny = deliveries.parallelStream().filter(c -> c.getName().equals(delivery.getName())).findAny();
        if(!findAny.isPresent()) {
            Delivery savedDelivery = deliveryDao.save(delivery);
            return new ResponseEntity<>(savedDelivery.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>(findAny.get().getId(), HttpStatus.OK);
    }

    @PutMapping("/deliveries/{id}")
    public ResponseEntity<?> update(@RequestBody Delivery delivery, @PathVariable Long id) {
        Optional<Delivery> deliveryOptional = deliveryDao.findById(id);
        if (!deliveryOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        delivery.setId(id);
        deliveryDao.save(delivery);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deliveries/{id}")
    public void delete(@PathVariable Long id) {
        deliveryDao.deleteById(id);
    }

    @GetMapping("/deliveries")
    public List<Delivery> retrieveAll() {
        return deliveryDao.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/deliveries/{id}")
    public Delivery retrieve(@PathVariable Long id) {
        Optional<Delivery> delivery = deliveryDao.findById(id);
        if (!delivery.isPresent()) {
            throw new DeliveryNotFoundException("Delivery not found with id -" + id);
        }
        return delivery.get();
    }
}