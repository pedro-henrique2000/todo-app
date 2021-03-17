package com.example.todoapp.service.user;

import com.example.todoapp.exception.NotFoundException;
import com.example.todoapp.model.Usuario;
import com.example.todoapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SearchUserByEmailAndPasswordService {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean validate(String email, String password) {
        Usuario usuario = repository.findByEmail(email).orElseThrow(() -> new NotFoundException("That account doesn't exists!"));
        return passwordEncoder.matches(password, usuario.getPassword());
    }

}
