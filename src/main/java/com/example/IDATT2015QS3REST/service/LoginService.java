package com.example.IDATT2015QS3REST.service;

import com.example.IDATT2015QS3REST.controller.LoginController;
import com.example.IDATT2015QS3REST.model.LoginRequest;
import com.example.IDATT2015QS3REST.model.LoginResponse;
import com.example.IDATT2015QS3REST.repository.LoginRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A service class for login
 */
@Service
public class LoginService {
    @Autowired
    LoginRepository loginRepository;

    private static final Logger LOGGER = LogManager.getLogger(LoginService.class);

    /**
     * A login method that sends the loginRequest to the repo
     * and receives a login response
     * @param loginRequest The login request
     * @return A login response
     */
    public LoginResponse doLoginRequest(LoginRequest loginRequest){
        LOGGER.info("Service: Logging in...");
        return loginRepository.findByLoginRequest(loginRequest);
    }
}
