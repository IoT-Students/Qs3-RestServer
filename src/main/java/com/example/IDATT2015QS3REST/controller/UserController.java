package com.example.IDATT2015QS3REST.controller;

import com.example.IDATT2015QS3REST.model.LoginRequest;
import com.example.IDATT2015QS3REST.model.LoginResponse;
import com.example.IDATT2015QS3REST.model.User;
import com.example.IDATT2015QS3REST.repository.UserRepository;
import com.example.IDATT2015QS3REST.service.LoginService;
import com.example.IDATT2015QS3REST.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public boolean createUser(@RequestBody User user) {
        LOGGER.info("Signing up..." + user.getUsername());
        userService.saveUser(new User(user.getUsername(), user.getPassw(), user.getRole()));
        return true;
    }
}
