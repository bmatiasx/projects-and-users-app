package com.example.projectsapp.controller;

import com.example.projectsapp.exception.ApiExceptionHandler;
import com.example.projectsapp.exception.ApiError;
import com.example.projectsapp.model.User;
import com.example.projectsapp.service.UserService;
import com.example.projectsapp.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@Slf4j
@RequestMapping("/v1/users")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService userService) {
        this.service = userService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<User> users  = service.findAll(pageable);

        log.info("User list found. Response status: {}", HttpStatus.OK);

        return ResponseEntity.ok(Util.convertToResponse(users, "users"));
    }

    @GetMapping(params = {"name", "email"})
    public ResponseEntity<?> findByNameAndEmail(@RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "email", required = false) String email) {
        User user;

        if (name != null) {
            user = service.findByName(name);

            log.info("User name={} found. Response status: {}", name, HttpStatus.OK);

            return ResponseEntity.ok(user);
        } else if (email != null) {
            user = service.findByEmail(email);

            log.info("User email={} found. Response status: {}", email, HttpStatus.OK);

            return ResponseEntity.ok(user);
        } else {
            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern(Util.TIME_PATTERN));

            log.error("No valid params provided. Response status: {}", HttpStatus.BAD_REQUEST);

            return new ApiError(date, HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(), ApiExceptionHandler.NO_PARAMS_PROVIDED_MESSAGE).toJSON();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        var user = service.findById(id);

        log.info("User id={} found. Response status: {}", id, HttpStatus.OK);

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user) {
        var newUser = service.create(user);

        log.info("{} created. Response status: {}", newUser.toString(), HttpStatus.CREATED);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody User user) {
        var updatedUser = service.update(user);

        log.info("Updated: {}. Response status: {}", updatedUser.toString(), HttpStatus.OK);

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);

        log.info("User with id={} deleted. Response status: {}", id, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
