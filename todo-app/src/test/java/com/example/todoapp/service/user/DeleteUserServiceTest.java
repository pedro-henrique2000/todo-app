package com.example.todoapp.service.user;

import com.example.todoapp.exception.NotFoundException;
import com.example.todoapp.model.Usuario;
import com.example.todoapp.repository.UsuarioRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class DeleteUserServiceTest {

    @Mock
    UsuarioRepository usuarioRepository;

    @InjectMocks
    DeleteUserService deleteUserService;

    @Test
    public void shouldDeleteUserWithSuccess() {
        Usuario usuario = new Usuario();
        Long id = 1L;

        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        deleteUserService.delete(id);

        Mockito.verify(usuarioRepository, Mockito.times(1)).delete(usuario);

    }

    @Test(expected = NotFoundException.class)
    @DisplayName("Should Throw NotFoundException When User Doesnt Exists")
    public void shouldThrowNotFoundExceptionWhenUserDoesntExists() {
        Long id = 99L;
        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.empty());
        try {
            deleteUserService.delete(id);
        } finally {
            Mockito.verify(usuarioRepository, Mockito.times(0)).delete(Mockito.any());
        }
    }
}
