package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.model.LoginRequest;
import com.example.IDATT2015QS3REST.model.LoginResponse;

/**
 * An Interface that represents a LoginRepository.
 * Handling events that has to do with login
 */

public interface LoginRepository {

    /**
     * A method for logging in sucessfully
     * Checks if user is registered in database
     * @param loginRequest loginRequest with username and password
     * @return returns a loginResponse with data if user is registered in database, if not returns a null object
     */

    LoginResponse findByLoginRequest(LoginRequest loginRequest);
}
