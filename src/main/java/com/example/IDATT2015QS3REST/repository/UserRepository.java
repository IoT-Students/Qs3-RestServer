package com.example.IDATT2015QS3REST.repository;

import com.example.IDATT2015QS3REST.model.User;

import java.util.List;

public interface UserRepository {
    int save(User user);

    List<User> findAll();

    User findById(int id);
}