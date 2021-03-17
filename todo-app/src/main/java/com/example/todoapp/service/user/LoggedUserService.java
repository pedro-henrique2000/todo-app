package com.example.todoapp.service.user;


import com.example.todoapp.exception.NotFoundException;
import com.example.todoapp.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoggedUserService {

    @Autowired
    private SearchUserByEmailService service;

    public Usuario getLoggedUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map(User.class::cast)
                .map(User::getUsername)
                .map(service::find)
                .orElseThrow(() -> new NotFoundException("Not found"));
    }
}


