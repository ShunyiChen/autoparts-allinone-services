package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.config.JwtTokenUtil;
import com.shunyi.autoparts.dao.UserDao;
import com.shunyi.autoparts.dao.UserShopMappingDao;
import com.shunyi.autoparts.exception.UserNotFoundException;
import com.shunyi.autoparts.model.User;
import com.shunyi.autoparts.model.UserShopMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** 用户控制器 */
@RestController
@CrossOrigin
public class UserController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    /** 用户Dao */
    @Autowired
    private UserDao userDao;
    /** 用户与店铺关系Dao */
    @Autowired
    private UserShopMappingDao userShopMappingDao;
    /** JWTToken工具 */
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User user) {
        User savedUser = userDao.save(user);
        return new ResponseEntity<>(savedUser.getId(), HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Long id) {
        Optional<User> packageOptional = userDao.findById(id);
        if (!packageOptional.isPresent())
            return ResponseEntity.notFound().build();
        user.setId(id);
        userDao.save(user);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/changepassword/{id}")
    public ResponseEntity<?> update(@RequestBody String newPassword, @PathVariable Long id) {
        Optional<User> packageOptional = userDao.findById(id);
        if (!packageOptional.isPresent())
            return ResponseEntity.notFound().build();
        userDao.updatePasswordByUserId(newPassword, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable Long id) {
        userDao.deleteById(id);
    }

    @GetMapping("/users")
    public List<User> retrieveAll() {
        return userDao.findAll(Sort.by(Sort.Direction.ASC, "id")).stream()
                .filter(e -> !e.getUsername().equalsIgnoreCase("root"))
                .collect(Collectors.toList());
    }

    @GetMapping("/users/shop/{sid}")
    public List<User> retrieveAllByShopId(@PathVariable Long sid) {
        List<UserShopMapping> list = userShopMappingDao.findAllByShopIdOrderByUserIdAsc(sid);
        List<User> lstUser = new ArrayList<>();
        list.forEach(e -> {
            Optional<User> opt = userDao.findById(e.getId().getUserId());
            if(opt.isPresent()) {
                lstUser.add(opt.get());
            }
        });
        return lstUser;
    }

    @GetMapping("/users/{id}")
    public User retrieve(@PathVariable Long id) {
        Optional<User> aUser = userDao.findById(id);
        if (!aUser.isPresent())
            throw new UserNotFoundException("User not found with id -" + id);
        return aUser.get();
    }

    @GetMapping("/users/username/{username}")
    public User retrieveByUserName(@PathVariable String username) {
        User user = userDao.findByUsername(username);
        if (user == null)
            throw new UserNotFoundException("User not found with id -" + user.getId());
        return user;
    }

}
