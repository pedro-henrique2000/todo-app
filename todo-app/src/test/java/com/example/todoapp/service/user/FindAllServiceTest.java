package com.example.todoapp.service.user;

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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class FindAllServiceTest {

    @Mock
    UsuarioRepository usuarioRepository;

    @InjectMocks
    FindAllService findAllService;

    @Test
    public void shouldReturnAllUsers() {
        List<Usuario> list = Arrays.asList(new Usuario(1L, "pedro1@teste.com", "pedro1", "bla", new ArrayList<>()),
                new Usuario(2L, "pedro1@teste.com", "pedro2", "bla", new ArrayList<>()),
                new Usuario(3L, "pedro1@teste.com", "pedro3", "bla", new ArrayList<>()));
        List<UsuarioResponse> expectedResponse = list.stream().map(usuario -> new UsuarioResponse(usuario.getName())).collect(Collectors.toList());

        Mockito.when(usuarioRepository.findAll()).thenReturn(list);

        List<UsuarioResponse> actualResponse = findAllService.findAll();

        Assert.assertNotNull(actualResponse);
        Assert.assertEquals(expectedResponse.size(), actualResponse.size());
        Assert.assertEquals(expectedResponse.get(0).getName(), actualResponse.get(0).getName());
    }
}
