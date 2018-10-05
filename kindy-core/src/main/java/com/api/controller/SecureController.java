package com.api.controller;

import com.api.model.User;
import com.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
@RequestMapping("/secure")
public class SecureController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String sayHello() {
        return "Secure Hello!";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")//Can define the roles for different APIs
    public User create(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.create(user);
    }

}
