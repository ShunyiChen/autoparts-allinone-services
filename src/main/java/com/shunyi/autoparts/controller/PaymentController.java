package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.PaymentDao;
import com.shunyi.autoparts.exception.PaymentNotFoundException;
import com.shunyi.autoparts.model.Payment;
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
 * @Description: 结算方式控制器
 * @Author: 陈顺谊
 * @CreateDate: 2020/4/18 7:57
 */
@RestController
@CrossOrigin
public class PaymentController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    private PaymentDao paymentDao;

    @PostMapping("/payments")
    public ResponseEntity<?> create(@RequestBody Payment payment) {
        List<Payment> payments = paymentDao.findAll();
        Optional<Payment> findAny = payments.parallelStream().filter(c -> c.getName().equals(payment.getName())).findAny();
        if(!findAny.isPresent()) {
            Payment savedPayment = paymentDao.save(payment);
            return new ResponseEntity<>(savedPayment.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>(findAny.get().getId(), HttpStatus.OK);
    }

    @PutMapping("/payments/{id}")
    public ResponseEntity<?> update(@RequestBody Payment payment, @PathVariable Long id) {
        Optional<Payment> carOptional = paymentDao.findById(id);
        if (!carOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        payment.setId(id);
        paymentDao.save(payment);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/payments/{id}")
    public void delete(@PathVariable Long id) {
        paymentDao.deleteById(id);
    }

    @GetMapping("/payments")
    public List<Payment> retrieveAll() {
        return paymentDao.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/payments/{id}")
    public Payment retrieve(@PathVariable Long id) {
        Optional<Payment> payment = paymentDao.findById(id);
        if (!payment.isPresent()) {
            throw new PaymentNotFoundException("Payment not found with id -" + id);
        }
        return payment.get();
    }
}
