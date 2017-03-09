package com.saspes.admin.controller;

import com.saspes.admin.model.UserAccount;
import com.saspes.admin.repository.UserRepository;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @GetMapping("/new")
    public ModelAndView users(ModelAndView model) {
        UserAccount user = new UserAccount();
        model.addObject("user", user);
        model.setViewName("users");
        return model;
    }
    
    @PostMapping("/save")
    public void usersSubmit(@ModelAttribute UserAccount user, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        userRepository.save(user);
        ControllerHelper.constructRedirectURL("/user/list", request, response);
    }
    
}
