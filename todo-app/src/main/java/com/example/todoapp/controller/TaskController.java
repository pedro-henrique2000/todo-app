package com.example.todoapp.controller;

import com.example.todoapp.model.request.TaskRequest;
import com.example.todoapp.model.response.TaskResponse;
import com.example.todoapp.service.tasks.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(description="Operations pertaining to create, delete and search for tasks")
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
    @ApiOperation(value = "Create a new Task")
    public TaskResponse create(@Valid @RequestBody TaskRequest taskRequest) {
        return createTaskService.create(taskRequest);
    }

    @DeleteMapping("/task/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete the a specific task that constains the Id passed on URI")
    public void delete(@PathVariable  Long id) {
        deleteTaskService.delete(id);
    }

    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find all the tasks of the logged user")
    public List<TaskResponse> findAll() {
        return findTasksByUserService.findAll();
    }

    @PostMapping("/task/{id}/finish")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Finish the task that contains the id specified on the URI")
    public void finish(@PathVariable Long id) {
        finishTaskService.finishTask(id);
    }

    @GetMapping("/tasks/{page}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find all the tasks of the logged user (paginated)")
    public List<TaskResponse> findPaginated(@PathVariable int page) {
        return findTaskByUserPaginatedService.findPaginated(page);
    }

}
