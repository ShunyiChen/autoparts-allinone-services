package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.CargoSpaceDao;
import com.shunyi.autoparts.dao.WarehouseDao;
import com.shunyi.autoparts.exception.CargoSpaceNotFoundException;
import com.shunyi.autoparts.model.Slot;
import com.shunyi.autoparts.model.Warehouse;
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

/** 货位控制器 */
@RestController
@CrossOrigin
public class CargoSpaceController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(CargoSpaceController.class);
    @Autowired
    private CargoSpaceDao cargoSpaceDao;
    @Autowired
    private WarehouseDao warehouseDao;

    @PostMapping("/cargoSpaces")
    public ResponseEntity<?> create(@RequestBody Slot cargoSpace) {
        Slot savedCargoSpace = cargoSpaceDao.save(cargoSpace);
        return new ResponseEntity<>(savedCargoSpace.getId(), HttpStatus.OK);
    }

    @PutMapping("/cargoSpaces/{id}")
    public ResponseEntity<?> update(@RequestBody Slot cargoSpace, @PathVariable Long id) {
        Optional<Slot> cargoSpaceOptional = cargoSpaceDao.findById(id);
        if (!cargoSpaceOptional.isPresent())
            return ResponseEntity.notFound().build();
        cargoSpace.setId(id);
        cargoSpaceDao.save(cargoSpace);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/cargoSpaces/{id}")
    public void delete(@PathVariable Long id) {
        cargoSpaceDao.deleteById(id);
    }

    @GetMapping("/cargoSpaces")
    public List<Slot> retrieveAll() {
        return cargoSpaceDao.findAll();
    }

    @GetMapping("/cargoSpaces/{id}")
    public Slot retrieve(@PathVariable Long id) {
        Optional<Slot> cargoSpace = cargoSpaceDao.findById(id);
        if (!cargoSpace.isPresent())
            throw new CargoSpaceNotFoundException("Slot not found with id -" + id);
        return cargoSpace.get();
    }

    @GetMapping("/cargoSpaces/warehouse/{pid}")
    public List<Slot> retrieveAll(@PathVariable Long pid) {
        List<Warehouse> allCategories = warehouseDao.findAllByOrderByIdAsc();
        Set<Long> idSet = new HashSet<>();
        idSet.add(pid);
        getNodes(pid, allCategories, idSet);
        Specification<Slot> specification = new Specification<Slot>() {
            @Override
            public Predicate toPredicate(Root<Slot> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                Path<Long> path = root.get("warehouse");
                CriteriaBuilder.In<Long> in = cb.in(path);
                idSet.stream().forEach(e -> {
                    in.value(e.longValue());
                });
                predicates.add(in);
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        return cargoSpaceDao.findAll(specification, sort);
    }

    /**
     *
     * @param pid
     * @param all
     * @param idSet
     */
    private void getNodes(Long pid, List<Warehouse> all, Set<Long> idSet) {
        for(Warehouse sc : all) {
            if(sc.getParentId() == pid) {
                idSet.add(sc.getId());
                getNodes(sc.getId(), all, idSet);
            }
        }
    }

    @PostMapping("/cargoSpaces/search")
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
        return cargoSpaceDao.findAll(specification, sort);
    }
}
