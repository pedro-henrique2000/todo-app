package com.example.todoapp.service.user;

import com.example.todoapp.exception.InvalidException;
import com.example.todoapp.exception.NotFoundException;
import com.example.todoapp.model.Usuario;
import com.example.todoapp.model.response.UsuarioResponse;
import com.example.todoapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchUserByIdService {

    @Autowired
    public UsuarioRepository repository;

    public UsuarioResponse searchById(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Not found a user with id %d", id)));
        UsuarioResponse response = new UsuarioResponse();
        response.setName(usuario.getName());

        return response;

    }

}
