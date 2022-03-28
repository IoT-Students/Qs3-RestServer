package com.example.IDATT2015QS3REST.service;

import com.example.IDATT2015QS3REST.model.LoginRequest;
import com.example.IDATT2015QS3REST.model.LoginResponse;
import com.example.IDATT2015QS3REST.model.User;
import com.example.IDATT2015QS3REST.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    UserRepository userRepository;

    public LoginResponse doLoginRequest(LoginRequest loginRequest){
        return userRepository.findByLoginRequest(loginRequest);
    }

    public int saveUser(User user){
        return userRepository.save(user);
    }
}
