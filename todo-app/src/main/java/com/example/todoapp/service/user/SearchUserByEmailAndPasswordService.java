package com.example.todoapp.service.user;

import com.example.todoapp.exception.InvalidException;
import com.example.todoapp.model.Usuario;
import com.example.todoapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SearchUserByEmailAndPasswordService {

    @Autowired
    UsuarioRepository repository;

    public boolean validate(String email, String password) {
        Usuario usuario = repository.findByEmail(email).orElseThrow(() -> new InvalidException("Invalid"));
        return new BCryptPasswordEncoder().matches(password, usuario.getPassword());
    }

}
