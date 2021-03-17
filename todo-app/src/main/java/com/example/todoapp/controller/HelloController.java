package com.example.todoapp.controller;

import com.example.todoapp.service.user.LoggedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    LoggedUserService loggedUserService;

    @GetMapping("/helloApi")
    public String hello() {
        return loggedUserService.getLoggedUser().getName();
    }

}
