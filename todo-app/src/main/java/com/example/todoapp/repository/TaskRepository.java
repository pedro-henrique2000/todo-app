package com.example.todoapp.repository;

import com.example.todoapp.model.Task;
import com.example.todoapp.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
    List<Task> findByUsuario(Usuario usuario);
    Page<Task> findByUsuario(Usuario usuario, Pageable page);
}
