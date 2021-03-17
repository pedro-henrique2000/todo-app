package com.example.todoapp.service.tasks;

import com.example.todoapp.exception.InvalidException;
import com.example.todoapp.model.Task;
import com.example.todoapp.model.Usuario;
import com.example.todoapp.model.response.TaskResponse;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.repository.UsuarioRepository;
import com.example.todoapp.service.user.LoggedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CreateTaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    LoggedUserService loggedUserService;

    public TaskResponse create(Task task) {
        if(task.getConclusionPrevision().isBefore(LocalDate.now()))
            throw new InvalidException("Conclusion Prevision must be in the future!");

        Usuario usuario = loggedUserService.getLoggedUser();
        task.setHasFinished(false);
        task.setUsuario(usuario);
        usuario.getTaskList().add(task);

        taskRepository.save(task);

        return new TaskResponse(task.getDescription(), task.getConclusionPrevision(), task.getPriority());

    }


}
