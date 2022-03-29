package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.model.LoginRequest;
import com.example.IDATT2015QS3REST.model.LoginResponse;

public interface LoginRepository {

    LoginResponse findByLoginRequest(LoginRequest loginRequest);
}
