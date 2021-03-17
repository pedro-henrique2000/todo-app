package com.example.todoapp.service.tasks;

import com.example.todoapp.exception.InvalidException;
import com.example.todoapp.model.Task;
import com.example.todoapp.model.Usuario;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.service.user.LoggedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeleteTaskService {

    @Autowired
    LoggedUserService loggedUserService;

    @Autowired
    TaskRepository taskRepository;

    public void delete(Long id) {
        Usuario usuario = loggedUserService.getLoggedUser();
        Task task = usuario.getTaskList().stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() -> new InvalidException(String.format("Not found a task with id %d", id)));

        List<Task> newTaskList = usuario.getTaskList().stream()
                .filter(t -> t.getId() != id)
                .collect(Collectors.toList());

        usuario.setTaskList(newTaskList);

        taskRepository.delete(task);
    }
}
