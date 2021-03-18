package com.example.todoapp.service.user;

import com.example.todoapp.exception.InvalidException;
import com.example.todoapp.exception.NotFoundException;
import com.example.todoapp.model.Usuario;
import com.example.todoapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SearchUserByEmailService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario find(String email){
        Optional<Usuario> userOptional = usuarioRepository.findByEmail(email);

        if(!userOptional.isPresent())
            throw new NotFoundException(String.format("Not found a user with email %s", email));

        return userOptional.get();
    }

}
