package com.example.projectsapp.projectsapp.controller;

import com.example.projectsapp.projectsapp.exception.ApiError;
import com.example.projectsapp.projectsapp.model.User;
import com.example.projectsapp.projectsapp.service.UserService;
import com.example.projectsapp.projectsapp.util.Util;
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

import static com.example.projectsapp.projectsapp.exception.ApiExceptionHandler.NO_PARAMS_PROVIDED_MESSAGE;

@RestController
@Slf4j
@RequestMapping("/api/v1/users") // TODO update path same way than ProjectController.java
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService userService) {
        this.service = userService;
    }

    @GetMapping(value = "/all") // TODO update path same way than ProjectController.java
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<User> users  = service.findAll(pageable);

        log.info(String.format("User list found. Response status: %s", HttpStatus.OK));

        return ResponseEntity.ok(Util.convertToResponse(users, "users"));
    }

    @GetMapping
    public ResponseEntity<?> findByNameAndEmail(@RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "email", required = false) String email) {
        User user;

        if (name != null) {
            user = service.findByName(name);

            log.info(String.format("User name=%s found. Response status: %s", name, HttpStatus.OK));

            return ResponseEntity.ok(user);
        } else if (email != null) {
            user = service.findByEmail(email);

            log.info(String.format("User email=%s found. Response status: %s", email, HttpStatus.OK));

            return ResponseEntity.ok(user);
        } else {
            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern(Util.TIME_PATTERN));

            log.error(String.format("No valid params provided. Response status: %s", HttpStatus.BAD_REQUEST));

            return new ApiError(date, HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(), NO_PARAMS_PROVIDED_MESSAGE).toJSON();
        }

    }

    @GetMapping("/{id}")// TODO update path same way than ProjectController.java
    public ResponseEntity<?> findById(@PathVariable long id) {
        var user = service.findById(id);

        log.info(String.format("User id=%s found. Response status: %s", id, HttpStatus.OK));

        return ResponseEntity.ok(user);
    }

    @PostMapping// TODO update path same way than ProjectController.java
    public ResponseEntity<?> create(@RequestBody User user) {
        var newUser = service.create(user);

        log.info(String.format("%s created. Response status: %s", newUser.toString(), HttpStatus.CREATED));

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping// TODO update path same way than ProjectController.java
    public ResponseEntity<?> update(@RequestBody User user) {
        var updatedUser = service.update(user);

        log.info(String.format("Updated: %s. Response status: %s", updatedUser.toString(), HttpStatus.OK));

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}") // TODO update path same way than ProjectController.java
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);

        log.info(String.format("User with id=%s deleted. Response status: %s", id, HttpStatus.OK));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
