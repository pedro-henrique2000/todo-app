package com.example.todoapp.service.task;

import com.example.todoapp.exception.NotFoundException;
import com.example.todoapp.model.Priority;
import com.example.todoapp.model.Task;
import com.example.todoapp.model.Usuario;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.service.tasks.FinishTaskService;
import com.example.todoapp.service.user.LoggedUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class FinishTaskServiceTest {

    @Mock
    LoggedUserService loggedUserService;

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    FinishTaskService finishTaskService;

    @Test
    public void shouldFinishTheTaskWithSuccess() {
        Usuario usuario = new Usuario(1L, "pedro@teste" , "pedro", "123", new ArrayList<>());
        Task task = new Task(1L, "Fix the door", LocalDate.now().plusDays(5), false, Priority.MODERATE, usuario);
        usuario.getTaskList().add(task);

        Mockito.when(loggedUserService.getLoggedUser()).thenReturn(usuario);

        finishTaskService.finishTask(1L);

        Assert.assertTrue(usuario.getTaskList().stream().filter(t -> t.getId() == 1L).findFirst().get().isHasFinished());
        Mockito.verify(taskRepository, Mockito.times(1)).save(task);
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundWhenTheTaskDoesntExist() {
        Usuario usuario = new Usuario(1L, "pedro@teste" , "pedro", "123", new ArrayList<>());
        Task task = new Task(1L, "Fix the door", LocalDate.now().plusDays(5), false, Priority.MODERATE, usuario);
        usuario.getTaskList().add(task);

        Mockito.when(loggedUserService.getLoggedUser()).thenReturn(usuario);

        try {
            finishTaskService.finishTask(2L);
        } catch(NotFoundException ex) {
            Assert.assertEquals(String.format("Not found a task with id %d", 2L), ex.getMessage());
            Mockito.verify(taskRepository, Mockito.times(0)).save(task);
            throw ex;
        }
    }
}
