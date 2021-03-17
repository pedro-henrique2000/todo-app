package com.example.todoapp.service.user;

import com.example.todoapp.exception.InvalidException;
import com.example.todoapp.model.Usuario;
import com.example.todoapp.model.request.UsuarioRequest;
import com.example.todoapp.model.response.UsuarioResponse;
import com.example.todoapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterUserService {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UsuarioResponse register(UsuarioRequest request) {
        if(!request.getPassword().equals(request.getConfirmPassword()))
            throw new InvalidException("Passwords aren't equals!");

        Optional<Usuario> optionalUsuario = repository.findByEmail(request.getEmail());

        if(optionalUsuario.isPresent())
            throw new InvalidException("Email already in use!");

        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setName(request.getName());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));

        repository.save(usuario);

        return new UsuarioResponse(usuario.getName());
    }

}
