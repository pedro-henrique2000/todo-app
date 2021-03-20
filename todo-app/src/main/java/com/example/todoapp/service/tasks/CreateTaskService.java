package com.example.todoapp.service.tasks;

import com.example.todoapp.exception.InvalidException;
import com.example.todoapp.model.Task;
import com.example.todoapp.model.Usuario;
import com.example.todoapp.model.request.TaskRequest;
import com.example.todoapp.model.response.TaskResponse;
import com.example.todoapp.repository.TaskRepository;
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

    public TaskResponse create(TaskRequest taskRequest) {
        if(taskRequest.getConclusionPrevision().isBefore(LocalDate.now()))
            throw new InvalidException("Conclusion Prevision must be in the future!");

        Usuario usuario = loggedUserService.getLoggedUser();
        Task task = new Task();
        task.setPriority(taskRequest.getPriority());
        task.setDescription(taskRequest.getDescription());
        task.setConclusionPrevision(taskRequest.getConclusionPrevision());
        task.setHasFinished(false);
        task.setUsuario(usuario);
        usuario.getTaskList().add(task);

        taskRepository.save(task);

        return new TaskResponse(task.getId(), task.getDescription(), task.getConclusionPrevision(), task.getPriority(), task.isHasFinished());

    }
}
