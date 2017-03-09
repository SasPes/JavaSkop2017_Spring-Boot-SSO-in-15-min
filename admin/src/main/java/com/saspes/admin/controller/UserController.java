package com.saspes.admin.controller;

import com.saspes.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserRepository userRepository;
    
    @GetMapping("/list")
    public ModelAndView usersAll(ModelAndView model) {
        model.addObject("users", userRepository.findAll());
        model.setViewName("usersList");
        return model;
    }
    
}
