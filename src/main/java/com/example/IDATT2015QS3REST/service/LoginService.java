package com.example.IDATT2015QS3REST.service;

import com.example.IDATT2015QS3REST.model.LoginRequest;
import com.example.IDATT2015QS3REST.model.LoginResponse;
import com.example.IDATT2015QS3REST.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    LoginRepository loginRepository;

    public LoginResponse doLoginRequest(LoginRequest loginRequest){
        return loginRepository.findByLoginRequest(loginRequest);
    }
}
