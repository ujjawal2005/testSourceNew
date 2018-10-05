package com.api.service;

import com.api.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User delete(String name);

    List<User> findAll();

    User findByUsername(String name);

    User update(User user);
}
