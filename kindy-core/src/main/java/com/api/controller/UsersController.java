package com.api.controller;

import com.api.model.Authority;
import com.api.model.User;
import com.api.security.Authorities;
import com.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.Set;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @ResponseBody
    @RequestMapping(value="/register",method = RequestMethod.POST)
    public User createUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActivated(true);
        Set<Authority> authoritiesSet=new HashSet<>();
        Authority auth=new Authority();
        auth.setName(Authorities.ROLE_USER.name());
        authoritiesSet.add(auth);
        user.setAuthorities(authoritiesSet);
        return userService.create(user);
    }

}
