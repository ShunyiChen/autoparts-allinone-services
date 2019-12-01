package com.shunyi.autoparts.controller;

import com.shunyi.autoparts.dao.UserDao;
import com.shunyi.autoparts.exception.UserNotFoundException;
import com.shunyi.autoparts.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/** 用户控制器 */
@RestController
@CrossOrigin
public class UserController {
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    /** 用户Dao */
    @Autowired
    private UserDao userDao;

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

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable Long id) {
        userDao.deleteById(id);
    }

    @GetMapping("/users")
    public List<User> retrieveAll() {
        return userDao.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieve(@PathVariable Long id) {
        Optional<User> aUser = userDao.findById(id);
        if (!aUser.isPresent())
            throw new UserNotFoundException("User not found with id -" + id);
        return aUser.get();
    }
}
