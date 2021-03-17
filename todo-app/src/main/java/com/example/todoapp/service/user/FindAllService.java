package com.example.todoapp.service.user;

import com.example.todoapp.model.Usuario;
import com.example.todoapp.model.response.UsuarioResponse;
import com.example.todoapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAllService {

    @Autowired
    UsuarioRepository repository;

    public List<UsuarioResponse> findAll() {
        List<Usuario> usuarioList = repository.findAll();

        return usuarioList.stream()
                .map(usuario -> new UsuarioResponse(usuario.getName())).collect(Collectors.toList());
    }

}
