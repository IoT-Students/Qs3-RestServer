package com.example.IDATT2015QS3REST.repository;

import java.util.List;

import com.example.IDATT2015QS3REST.model.LoginRequest;
import com.example.IDATT2015QS3REST.model.LoginResponse;
import com.example.IDATT2015QS3REST.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserRepository implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(User user) {
        return jdbcTemplate.update("INSERT INTO users (username, passw, role) VALUES(?,?,?)",
                new Object[] {user.getUsername(), user.getPassw(), user.getRole()});
    }
    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * from users", BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public User findById(int id) {
        try {
            User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE userID=?",
                    BeanPropertyRowMapper.newInstance(User.class), id);

            return user;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public LoginResponse findByLoginRequest(LoginRequest loginRequest) {
        try {
            System.out.println("LogingRequest: " + loginRequest.getUsername() + ", " + loginRequest.getPassword());

            User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE username=? AND passw=?",
                    BeanPropertyRowMapper.newInstance(User.class),loginRequest.getUsername(),loginRequest.getPassword());

            System.out.println("Fant bruker med brukernavn:" + user.getUsername() + ",pass" + user.getPassw() + ",id " + user.getUserID() + " og " + user.getRole());
            return new LoginResponse("Success", user.getUserID(), user.getRole());

        } catch (IncorrectResultSizeDataAccessException e) {
            System.out.println("Fant ikke bruker");
            return new LoginResponse("Fail", 0, null);
        }
    }
}
