package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.ConsumerCategoryDao;
import com.shunyi.autoparts.dao.ConsumerDao;
import com.shunyi.autoparts.exception.ConsumerNotFoundException;
import com.shunyi.autoparts.model.Consumer;
import com.shunyi.autoparts.model.ConsumerCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * @description 客户Controller
 * @author Shunyi
 * @date 2020/5/12
 */
@RestController
@CrossOrigin
public class ConsumerController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);
    @Autowired
    private ConsumerDao consumerDao;
    @Autowired
    private ConsumerCategoryDao consumerCategoryDao;

    @PostMapping("/consumers")
    public ResponseEntity<?> create(@RequestBody Consumer consumer) {
        List<Consumer> consumers = consumerDao.findAll();
        Optional<Consumer> findAny = consumers.parallelStream().filter(c -> c.getName().equals(consumer.getName())).findAny();
        if(!findAny.isPresent()) {
            Consumer savedConsumer = consumerDao.save(consumer);
            return new ResponseEntity<>(savedConsumer.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>(findAny.get().getId(), HttpStatus.OK);
    }

    @PutMapping("/consumers/{id}")
    public ResponseEntity<?> update(@RequestBody Consumer consumer, @PathVariable Long id) {
        Optional<Consumer> consumerOptional = consumerDao.findById(id);
        if (!consumerOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        consumer.setId(id);
        consumerDao.save(consumer);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/consumers/{id}")
    public void delete(@PathVariable Long id) {
        consumerDao.deleteById(id);
    }

    @GetMapping("/consumers")
    public List<Consumer> retrieveAll() {
        return consumerDao.findAll();
    }

    @GetMapping("/consumers/{id}")
    public Consumer retrieve(@PathVariable Long id) {
        Optional<Consumer> consumer = consumerDao.findById(id);
        if (!consumer.isPresent()) {
            throw new ConsumerNotFoundException("Consumer not found with id -" + id);
        }
        return consumer.get();
    }

    /** 递归获取 */
    @GetMapping("/consumers/category/recursion/{pid}")
    public List<Consumer> retrieveAll_R(@PathVariable Long pid) {
        List<ConsumerCategory> allCategories = consumerCategoryDao.findAllByOrderByIdAsc();
        Set<Long> idSet = new HashSet<>();
        idSet.add(pid);
        getNodes(pid, allCategories, idSet);
        Specification<Consumer> specification = new Specification<Consumer>() {
            @Override
            public Predicate toPredicate(Root<Consumer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                Path<Long> path = root.get("consumerCategory");
                CriteriaBuilder.In<Long> in = cb.in(path);
                idSet.stream().forEach(e -> {
                    in.value(e.longValue());
                });
                predicates.add(in);
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        return consumerDao.findAll(specification, sort);
    }

    /** 非递归获取 */
    @GetMapping("/consumers/category/{pid}")
    public List<Consumer> retrieveAll(@PathVariable Long pid) {
        return consumerDao.findAllByConsumerCategoryIdOrderByIdAsc(pid);
    }

    @PostMapping("/consumers/search")
    public List<Consumer> search(@RequestBody Consumer consumer) {
        Specification<Consumer> specification = new Specification<Consumer>() {
            @Override
            public Predicate toPredicate(Root<Consumer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(consumer.getCode() != null && !consumer.getCode().equals("")) {
                    Path<String> path = root.get("code");
                    Predicate predicate = cb.like(path, "%"+consumer.getCode()+"%");
                    predicates.add(predicate);
                }
                if(consumer.getName() != null && !consumer.getName().equals("")) {
                    Path<String> path = root.get("name");
                    Predicate predicate = cb.like(path, "%"+consumer.getName()+"%");
                    predicates.add(predicate);
                }
                if(consumer.getContact() != null && !consumer.getContact().equals("")) {
                    Path<String> path = root.get("contact");
                    Predicate predicate = cb.like(path, "%"+consumer.getContact()+"%");
                    predicates.add(predicate);
                }
                if(consumer.getPhone() != null && !consumer.getPhone().equals("")) {
                    Path<String> path = root.get("phone");
                    Predicate predicate = cb.like(path, "%"+consumer.getPhone()+"%");
                    predicates.add(predicate);
                }
                if(consumer.getEmail() != null && !consumer.getEmail().equals("")) {
                    Path<String> path = root.get("email");
                    Predicate predicate = cb.like(path, "%"+consumer.getEmail()+"%");
                    predicates.add(predicate);
                }
//                if(consumer.getAddress() != null && !consumer.getAddress().equals("")) {
//                    Path<String> path = root.get("address");
//                    Predicate predicate = cb.like(path, "%"+consumer.getAddress()+"%");
//                    predicates.add(predicate);
//                }
//                if(consumer.getPostCode() != null && !consumer.getPostCode().equals("")) {
//                    Path<String> path = root.get("postCode");
//                    Predicate predicate = cb.like(path, "%"+consumer.getPostCode()+"%");
//                    predicates.add(predicate);
//                }
//                if(consumer.getNotes()!= null && !consumer.getNotes().equals("")) {
//                    Path<String> path = root.get("notes");
//                    Predicate predicate = cb.like(path, "%"+consumer.getNotes()+"%");
//                    predicates.add(predicate);
//                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        return consumerDao.findAll(specification, sort);
    }

    /**
     *
     * @param pid
     * @param all
     * @param idSet
     */
    private void getNodes(Long pid, List<ConsumerCategory> all, Set<Long> idSet) {
        for(ConsumerCategory sc : all) {
            if(sc.getParentId() == pid) {
                idSet.add(sc.getId());
                getNodes(sc.getId(), all, idSet);
            }
        }
    }

}
