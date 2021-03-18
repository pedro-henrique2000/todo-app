package com.example.todoapp.service.tasks;

import com.example.todoapp.exception.NotFoundException;
import com.example.todoapp.model.Task;
import com.example.todoapp.model.Usuario;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.service.user.LoggedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new NotFoundException(String.format("Not found a task with id %d", id)));

        usuario.getTaskList().removeIf(t -> t.getId() == id);

        taskRepository.delete(task);
    }
}
