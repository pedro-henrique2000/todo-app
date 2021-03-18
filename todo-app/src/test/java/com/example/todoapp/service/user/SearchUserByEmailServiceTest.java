package com.example.todoapp.service.user;

import com.example.todoapp.exception.NotFoundException;
import com.example.todoapp.model.Usuario;
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
public class SearchUserByEmailServiceTest {

    @Mock
    UsuarioRepository usuarioRepository;

    @InjectMocks
    SearchUserByEmailService searchUserByEmailService;

    @Test
    public void shouldReturnTheCorrectUser() {
        String email = "pedro@teste.com";
        Usuario usuario = new Usuario(1L, email , "pedro", "123", new ArrayList<>());
        Mockito.when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));

        Usuario response = searchUserByEmailService.find(email);

        Assert.assertNotNull(response);
        Assert.assertEquals(usuario.getEmail(), response.getEmail());
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundExceptionWhenTheUserDoesntExists() {
        String email = "pedro@teste.com";
        Mockito.when(usuarioRepository.findByEmail(email)).thenReturn(Optional.empty());

        try {
            searchUserByEmailService.find(email);
        } catch(NotFoundException ex) {
            Assert.assertEquals(String.format("Not found a user with email %s", email), ex.getMessage());
            throw ex;
        }
    }
}
