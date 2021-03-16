package com.example.todoapp.controller;

import com.example.todoapp.model.request.UsuarioRequest;
import com.example.todoapp.model.response.UsuarioResponse;
import com.example.todoapp.service.user.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    RegisterUserService service;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse register(@Valid @RequestBody UsuarioRequest request) {
        return service.register(request);
    }

}
