package com.api.service.impl;

import com.api.model.User;
import com.api.repository.UserRepository;
import com.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public User delete(String name) {
        User user = findByUsername(name);
        if(user != null){
            repository.delete(user);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findByUsername(String name) {
        return repository.findOne(name);
    }

    @Override
    public User update(User user) {
        return null;
    }
}
