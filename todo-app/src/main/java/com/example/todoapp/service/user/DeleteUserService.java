package com.example.todoapp.service.user;

import com.example.todoapp.exception.InvalidException;
import com.example.todoapp.model.Usuario;
import com.example.todoapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserService {

    @Autowired
    UsuarioRepository repository;

    public void delete(Long id) {
        Usuario deleteUsuario = repository.findById(id).orElseThrow(() -> new InvalidException());
        repository.delete(deleteUsuario);
    }


}
