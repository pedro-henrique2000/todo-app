package com.example.todoapp.controller;

import com.example.todoapp.model.request.UsuarioRequest;
import com.example.todoapp.model.response.UsuarioResponse;
import com.example.todoapp.service.user.DeleteUserService;
import com.example.todoapp.service.user.FindAllService;
import com.example.todoapp.service.user.RegisterUserService;
import com.example.todoapp.service.user.SearchUserByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
public class UserController {

    @Autowired
    RegisterUserService registerUserService;

    @Autowired
    SearchUserByIdService searchUserByIdService;

    @Autowired
    DeleteUserService deleteUserService;

    @Autowired
    FindAllService findAllService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse register(@Valid @RequestBody UsuarioRequest request) {
        return registerUserService.register(request);
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponse searchById(@PathVariable Long id) {
        return searchUserByIdService.searchById(id);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        deleteUserService.delete(id);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioResponse> findAll() {
        return findAllService.findAll();
    }

}
