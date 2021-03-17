package com.example.todoapp.model.response;

import com.example.todoapp.model.Priority;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TaskResponse {

    private String description;
    private LocalDate conclusionPrevision;
    private Priority priority;

}
