package com.example.todoapp.service.tasks;

import com.example.todoapp.model.Task;
import com.example.todoapp.model.Usuario;
import com.example.todoapp.model.response.TaskResponse;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.service.user.LoggedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindTasksByUserService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    LoggedUserService loggedUserService;

    public List<TaskResponse> findAll() {
        Usuario usuario = loggedUserService.getLoggedUser();

        List<Task> taskList = taskRepository.findByUsuario(usuario);

        return taskList.stream()
                .map(t -> new TaskResponse(t.getId(), t.getDescription(), t.getConclusionPrevision(), t.getPriority(), t.isHasFinished()))
                .collect(Collectors.toList());
    }

}
