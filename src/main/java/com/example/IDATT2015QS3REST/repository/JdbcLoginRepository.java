package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.controller.LoginController;
import com.example.IDATT2015QS3REST.model.LoginRequest;
import com.example.IDATT2015QS3REST.model.LoginResponse;
import com.example.IDATT2015QS3REST.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * A class thar represent a repository for handling login events
 */
@Repository
public class JdbcLoginRepository implements LoginRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger LOGGER = LogManager.getLogger(JdbcLoginRepository.class);

    /**
     * A method for logging in sucessfully
     * Checks if user is registered in database
     * @param loginRequest loginRequest with username and password
     * @return returns a loginResponse with data if user is registered in database, if not returns a null object
     */

    @Override
    public LoginResponse findByLoginRequest(LoginRequest loginRequest) {
        LOGGER.info("Logging in with user " + loginRequest.getUsername());
        try {
            User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE username=? AND passw=?",
                    BeanPropertyRowMapper.newInstance(User.class),loginRequest.getUsername(),loginRequest.getPassword());

            LOGGER.info("Log in was successful");
            return new LoginResponse("Success", user.getUserID(), user.getRole(), user.getName(), user.getEmail(), user.getLastName());

        } catch (IncorrectResultSizeDataAccessException e) {
            LOGGER.warn("Wrong username or password");
            return new LoginResponse("Fail");
        }
    }

}
