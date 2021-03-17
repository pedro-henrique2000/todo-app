package com.example.todoapp.controller;

import com.example.todoapp.model.Task;
import com.example.todoapp.model.response.TaskResponse;
import com.example.todoapp.service.tasks.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    CreateTaskService createTaskService;

    @Autowired
    DeleteTaskService deleteTaskService;

    @Autowired
    FindTasksByUserService findTasksByUserService;

    @Autowired
    FinishTaskService finishTaskService;

    @Autowired
    FindTaskByUserPaginatedService findTaskByUserPaginatedService;

    @PostMapping("/task/create")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponse create(@RequestBody Task taskRequest) {
        return createTaskService.create(taskRequest);
    }

    @DeleteMapping("/task/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable  Long id) {
        deleteTaskService.delete(id);
    }

    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskResponse> findAll() {
        return findTasksByUserService.findAll();
    }

    @PostMapping("/task/{id}/finish")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finish(@PathVariable Long id) {
        finishTaskService.finishTask(id);
    }

    @GetMapping("/tasks/{page}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskResponse> findPaginated(@PathVariable int page) {
        return findTaskByUserPaginatedService.findPaginated(page);
    }


}
