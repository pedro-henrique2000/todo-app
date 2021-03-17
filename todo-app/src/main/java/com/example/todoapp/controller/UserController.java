package com.example.todoapp.controller;

import com.example.todoapp.model.request.UsuarioRequest;
import com.example.todoapp.model.response.UsuarioResponse;
import com.example.todoapp.service.user.DeleteUserService;
import com.example.todoapp.service.user.FindAllService;
import com.example.todoapp.service.user.RegisterUserService;
import com.example.todoapp.service.user.SearchUserByIdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@Api(description="Operations pertaining to create, delete and search for users")
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
    @ApiOperation(value = "Create a new user")
    public UsuarioResponse register(@Valid @RequestBody UsuarioRequest request) {
        return registerUserService.register(request);
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Search a user with the id specified on the URI")
    public UsuarioResponse searchById(@PathVariable Long id) {
        return searchUserByIdService.searchById(id);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a user with the id specified on the URI")
    public void delete(@PathVariable Long id) {
        deleteUserService.delete(id);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find all users")
    public List<UsuarioResponse> findAll() {
        return findAllService.findAll();
    }

}
