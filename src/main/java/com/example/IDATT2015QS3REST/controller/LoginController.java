package com.example.IDATT2015QS3REST.controller;

import com.example.IDATT2015QS3REST.model.LoginRequest;
import com.example.IDATT2015QS3REST.model.LoginResponse;
import com.example.IDATT2015QS3REST.model.User;
import com.example.IDATT2015QS3REST.repository.UserRepository;
import com.example.IDATT2015QS3REST.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * This is a controller class for login.
 * It receives a login request and respond with a login response
 */
@RestController
@RequestMapping(value = "/login")
@EnableAutoConfiguration
@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginService;

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

    /**
     * This is an endpoint for login. It receives a login request with username and password.
     * If the credentials checks out it returns a login response with userinfo, loginstatus and a token
     * @param loginRequest
     * @return loginresponse
     */
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public LoginResponse doLogin(final @RequestBody LoginRequest loginRequest) {
        LOGGER.info("Logging in..." + loginRequest.getUsername());

        return loginService.doLoginRequest(loginRequest);
    }
}
