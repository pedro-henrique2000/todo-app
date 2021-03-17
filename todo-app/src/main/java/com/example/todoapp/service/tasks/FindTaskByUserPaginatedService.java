package com.example.todoapp.service.tasks;

import com.example.todoapp.model.Task;
import com.example.todoapp.model.Usuario;
import com.example.todoapp.model.response.TaskResponse;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.service.user.LoggedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindTaskByUserPaginatedService {

    private static final int SIZE_PER_PAGE = 4;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    LoggedUserService loggedUserService;

    public List<TaskResponse> findPaginated(int pageNumber) {
        Usuario usuario = loggedUserService.getLoggedUser();

        Pageable page = PageRequest.of(pageNumber, SIZE_PER_PAGE);
        Page<Task> taskPage = taskRepository.findByUsuario(usuario, page);

        return taskPage.stream()
                .map(t -> new TaskResponse(t.getDescription(), t.getConclusionPrevision(), t.getPriority()))
                .collect(Collectors.toList());
    }


}
