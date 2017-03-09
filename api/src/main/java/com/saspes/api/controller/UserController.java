package com.saspes.api.controller;

import com.saspes.api.model.UserAccount;
import com.saspes.api.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserRepository userRepository;
    
    @GetMapping("/list")
    public List<UserAccount> findAll() {
	return userRepository.findAll();
    }
    
}
