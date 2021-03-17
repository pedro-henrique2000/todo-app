package com.example.todoapp.controller;

import com.example.todoapp.model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/helloApi")
    public Greeting hello() {
        return new Greeting("hello");
    }

}
