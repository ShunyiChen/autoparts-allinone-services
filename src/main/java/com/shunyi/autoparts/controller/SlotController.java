package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.SlotDao;
import com.shunyi.autoparts.dao.WarehouseDao;
import com.shunyi.autoparts.exception.SlotFoundException;
import com.shunyi.autoparts.model.Slot;
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
 * @description 货位控制器
 * @author Shunyi Chen
 * @date 2020/3/23
 */
@RestController
@CrossOrigin
public class SlotController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(SlotController.class);
    @Autowired
    private SlotDao slotDao;
    @Autowired
    private WarehouseDao warehouseDao;

    @PostMapping("/slot")
    public ResponseEntity<?> create(@RequestBody Slot slot) {
        Slot savedSlot = slotDao.save(slot);
        return new ResponseEntity<>(savedSlot.getId(), HttpStatus.OK);
    }

    @PutMapping("/slot/{id}")
    public ResponseEntity<?> update(@RequestBody Slot slot, @PathVariable Long id) {
        Optional<Slot> slotOptional = slotDao.findById(id);
        if (!slotOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        slot.setId(id);
        slotDao.save(slot);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/slot/{id}")
    public void delete(@PathVariable Long id) {
        slotDao.deleteById(id);
    }

    @GetMapping("/slot")
    public List<Slot> retrieveAll() {
        return slotDao.findAll();
    }

    @GetMapping("/slot/{id}")
    public Slot retrieve(@PathVariable Long id) {
        Optional<Slot> slot = slotDao.findById(id);
        if(!slot.isPresent()) {
            throw new SlotFoundException("Slot not found with id -" + id);
        }
        return slot.get();
    }

    @GetMapping("/slot/warehouse/{pid}")
    public List<Slot> retrieveAll(@PathVariable Long pid) {

        Specification<Slot> specification = new Specification<Slot>() {
            @Override
            public Predicate toPredicate(Root<Slot> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                Path<Long> path = root.get("warehouse").get("id");
                Predicate predicate = cb.equal(path, pid);
                predicates.add(predicate);
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        return slotDao.findAll(specification, sort);
    }

//    @GetMapping("/slot/warehouse/{pid}")
//    public List<Slot> retrieveAll(@PathVariable Long pid) {
//        List<Warehouse> allCategories = warehouseDao.findAllByOrderByIdAsc();
//        Set<Long> idSet = new HashSet<>();
//        idSet.add(pid);
//        getNodes(pid, allCategories, idSet);
//        Specification<Slot> specification = new Specification<Slot>() {
//            @Override
//            public Predicate toPredicate(Root<Slot> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                List<Predicate> predicates = new ArrayList<>();
//                Path<Long> path = root.get("warehouse");
//                CriteriaBuilder.In<Long> in = cb.in(path);
//                idSet.stream().forEach(e -> {
//                    in.value(e.longValue());
//                });
//                predicates.add(in);
//                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
//            }
//        };
//        Sort sort = Sort.by(Sort.Direction.ASC,"id");
//        return slotDao.findAll(specification, sort);
//    }
//
//    /**
//     *
//     * @param pid
//     * @param all
//     * @param idSet
//     */
//    private void getNodes(Long pid, List<Warehouse> all, Set<Long> idSet) {
//        for(Warehouse sc : all) {
//            if(sc.getParentId().equals(pid)) {
//                idSet.add(sc.getId());
//                getNodes(sc.getId(), all, idSet);
//            }
//        }
//    }

    @PostMapping("/slot/search")
    public List<Slot> search(@RequestBody Slot condition) {
        Specification<Slot> specification = new Specification<Slot>() {
            @Override
            public Predicate toPredicate(Root<Slot> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(!condition.getBarCode().equals("")) {
                    Path<String> path = root.get("barCode");
                    Predicate predicate = cb.like(path, "%"+condition.getBarCode()+"%");
                    predicates.add(predicate);
                }
                if(!condition.getName().equals("")) {
                    Path<String> path = root.get("name");
                    Predicate predicate = cb.like(path, "%"+condition.getName()+"%");
                    predicates.add(predicate);
                }
                if(!condition.getLevel_1().equals("")) {
                    Path<String> path = root.get("level_1");
                    Predicate predicate = cb.like(path, "%"+condition.getLevel_1()+"%");
                    predicates.add(predicate);
                }
                if(!condition.getLevel_2().equals("")) {
                    Path<String> path = root.get("level_2");
                    Predicate predicate = cb.like(path, "%"+condition.getLevel_2()+"%");
                    predicates.add(predicate);
                }
                if(!condition.getLevel_3().equals("")) {
                    Path<String> path = root.get("level_3");
                    Predicate predicate = cb.like(path, "%"+condition.getLevel_3()+"%");
                    predicates.add(predicate);
                }
                if(!condition.getLevel_4().equals("")) {
                    Path<String> path = root.get("level_4");
                    Predicate predicate = cb.like(path, "%"+condition.getLevel_4()+"%");
                    predicates.add(predicate);
                }
                if(!condition.getLevel_5().equals("")) {
                    Path<String> path = root.get("level_5");
                    Predicate predicate = cb.like(path, "%"+condition.getLevel_5()+"%");
                    predicates.add(predicate);
                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        return slotDao.findAll(specification, sort);
    }
}
