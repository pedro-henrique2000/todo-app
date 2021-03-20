package com.example.todoapp.service.task;

import com.example.todoapp.exception.InvalidException;
import com.example.todoapp.exception.NotFoundException;
import com.example.todoapp.model.Priority;
import com.example.todoapp.model.Usuario;
import com.example.todoapp.model.request.TaskRequest;
import com.example.todoapp.model.response.TaskResponse;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.service.tasks.CreateTaskService;
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
public class CreateTaskServiceTest {

    @Mock
    TaskRepository taskRepository;

    @Mock
    LoggedUserService loggedUserService;

    @InjectMocks
    CreateTaskService createTaskService;

    @Test
    public void shouldCreateATaskWithSuccess() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setDescription("Fix the door");
        taskRequest.setConclusionPrevision(LocalDate.now().plusDays(5));
        taskRequest.setPriority(Priority.MODERATE);

        Usuario usuario = new Usuario(1L, "pedro@teste" , "pedro", "123", new ArrayList<>());

        Mockito.when(loggedUserService.getLoggedUser()).thenReturn(usuario);

        TaskResponse expectedResponse =
                new TaskResponse(1L, taskRequest.getDescription(), taskRequest.getConclusionPrevision(), taskRequest.getPriority(), false);

        TaskResponse actualResponse = createTaskService.create(taskRequest);

        Assert.assertNotNull(actualResponse);
        Assert.assertEquals(expectedResponse.getDescription(), actualResponse.getDescription());
        Assert.assertEquals(1, usuario.getTaskList().size());

    }

    @Test(expected = InvalidException.class)
    public void shouldThrowInvalidExceptionWhenConclusionIsInThePast() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setDescription("Fix the door");
        taskRequest.setConclusionPrevision(LocalDate.now().minusDays(5));
        taskRequest.setPriority(Priority.MODERATE);

        try {
            createTaskService.create(taskRequest);
        } catch (InvalidException ex) {
            Assert.assertEquals("Conclusion Prevision must be in the future!", ex.getMessage());
            throw ex;
        }
    }

}
