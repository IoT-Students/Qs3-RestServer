package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.model.LoginRequest;
import com.example.IDATT2015QS3REST.model.LoginResponse;
import com.example.IDATT2015QS3REST.model.User;
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

    /**
     * A method for logging in sucessfully
     * Checks if user is registered in database
     * @param loginRequest loginRequest with username and password
     * @return returns a loginResponse with data if user is registered in database, if not returns a null object
     */

    @Override
    public LoginResponse findByLoginRequest(LoginRequest loginRequest) {
        try {
            System.out.println("LoginRequest: " + loginRequest.getUsername() + ", " + loginRequest.getPassword());


            User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE username=? AND passw=?",
                    BeanPropertyRowMapper.newInstance(User.class),loginRequest.getUsername(),loginRequest.getPassword());

            System.out.println("Fant bruker med brukernavn:" + user.getUsername() + ",pass" + user.getPassw() + ",id " + user.getUserID() + " og " + user.getRole());
            return new LoginResponse("Success", user.getUserID(), user.getRole(), user.getName(), user.getEmail(), user.getLastName());

        } catch (IncorrectResultSizeDataAccessException e) {
            System.out.println("Fant ikke bruker");
            return new LoginResponse("Fail", 0, null, null, null, null);
        }
    }

}
