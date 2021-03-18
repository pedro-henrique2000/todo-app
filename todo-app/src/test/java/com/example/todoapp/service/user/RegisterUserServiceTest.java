package com.example.todoapp.service.user;

import com.example.todoapp.exception.InvalidException;
import com.example.todoapp.model.Usuario;
import com.example.todoapp.model.request.UsuarioRequest;
import com.example.todoapp.model.response.UsuarioResponse;
import com.example.todoapp.repository.UsuarioRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class RegisterUserServiceTest {

    @Mock
    UsuarioRepository usuarioRepository;

    @InjectMocks
    RegisterUserService registerUserService;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Test should pass when the user is correct")
    public void shouldPassWhenUserIsCorrect() {
        UsuarioRequest usuarioRequest = new UsuarioRequest();
        usuarioRequest.setEmail("Pedro@teste.com");
        usuarioRequest.setPassword("123");
        usuarioRequest.setConfirmPassword("123");
        usuarioRequest.setName("Pedro");

        UsuarioResponse expected = new UsuarioResponse(usuarioRequest.getName());

        Mockito.when(usuarioRepository.findByEmail(usuarioRequest.getEmail())).thenReturn(Optional.empty());

        UsuarioResponse response = registerUserService.register(usuarioRequest);

        Assert.assertNotNull(response);
        Assert.assertEquals(expected.getName(), response.getName());

    }

    @Test(expected = InvalidException.class)
    @DisplayName("Should thorw InvalidException when password aren't equals")
    public void shouldThrowInvalidExceptionWhenPasswordsArentEquals() {
        UsuarioRequest usuarioRequest = new UsuarioRequest();
        usuarioRequest.setEmail("Pedro@teste.com");
        usuarioRequest.setPassword("123");
        usuarioRequest.setConfirmPassword("1222222");
        usuarioRequest.setName("Pedro");

        try {
            registerUserService.register(usuarioRequest);
        } catch(InvalidException exception) {
            Assert.assertEquals("Passwords aren't equals!", exception.getMessage());
            throw exception;
        }
    }

    @Test(expected = InvalidException.class)
    @DisplayName("Should throw InvalidException when try to insert duplicated email")
    public void shoudlThrowInvalidExceptionWhenDuplicatedEmail() {
        UsuarioRequest usuarioRequest = new UsuarioRequest();
        usuarioRequest.setEmail("Pedro@teste.com");
        usuarioRequest.setPassword("123");
        usuarioRequest.setConfirmPassword("123");
        usuarioRequest.setName("Pedro");

        Usuario alreadyCreatedUsuario = new Usuario();
        alreadyCreatedUsuario.setEmail("Pedro@teste.com");

        Mockito.when(usuarioRepository.findByEmail(usuarioRequest.getEmail())).thenReturn(Optional.of(alreadyCreatedUsuario));

        try {
            registerUserService.register(usuarioRequest);
        } catch(InvalidException exception) {
            Assert.assertEquals("Email already in use!", exception.getMessage());
            throw exception;
        }

    }
}
