package com.example.IDATT2015QS3REST.service;

import com.example.IDATT2015QS3REST.model.LoginRequest;
import com.example.IDATT2015QS3REST.model.LoginResponse;
import com.example.IDATT2015QS3REST.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A service class for login
 */
@Service
public class LoginService {
    @Autowired
    LoginRepository loginRepository;

    /**
     * A login method that sends the loginRequest to the repo
     * and receives a login response
     * @param loginRequest The login request
     * @return A login response
     */
    public LoginResponse doLoginRequest(LoginRequest loginRequest){
        return loginRepository.findByLoginRequest(loginRequest);
    }
}
