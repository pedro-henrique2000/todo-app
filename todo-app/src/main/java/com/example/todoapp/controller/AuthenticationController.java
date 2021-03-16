package com.example.todoapp.controller;

import com.example.todoapp.exception.InvalidException;
import com.example.todoapp.model.request.AuthRequest;
import com.example.todoapp.model.response.AuthResponse;
import com.example.todoapp.service.user.SearchUserByEmailAndPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    @Autowired
    SearchUserByEmailAndPasswordService searchUserByEmailAndPasswordService;

    @PostMapping("/authenticate")
    public AuthResponse login(@Valid @RequestBody AuthRequest authRequest) {
        if(!searchUserByEmailAndPasswordService.validate(authRequest.getEmail(), authRequest.getPassword()))
            throw new InvalidException("That account doesn't exists!");

        return new AuthResponse(authRequest.getEmail());
    }
}
