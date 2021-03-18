package com.example.todoapp.service.user;

import com.example.todoapp.exception.NotFoundException;
import com.example.todoapp.model.Usuario;
import com.example.todoapp.model.response.UsuarioResponse;
import com.example.todoapp.repository.UsuarioRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class SearchUserByIdServiceTest {

    @Mock
    UsuarioRepository usuarioRepository;

    @InjectMocks
    SearchUserByIdService searchUserByIdService;

    @Test
    public void shouldReturnTheCorrectUser() {
        Long id = 1L;
        Usuario usuario = new Usuario(id, "pedro@teste" , "pedro", "123", new ArrayList<>());
        UsuarioResponse expectedResponse = new UsuarioResponse(usuario.getName());

        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        UsuarioResponse actualResponse = searchUserByIdService.searchById(id);

        Assert.assertEquals(expectedResponse.getName(), actualResponse.getName());
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundWhenTheUserDoesntExist() {
        Long id = 1L;
        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        try {
            searchUserByIdService.searchById(id);
        } catch(NotFoundException ex) {
            Assert.assertEquals(String.format("Not found a user with id %d", id), ex.getMessage());
            throw  ex;
        }
    }
}
