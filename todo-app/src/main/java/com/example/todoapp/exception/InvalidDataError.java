package com.example.todoapp.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InvalidDataError {

    private LocalDateTime timestamp;
    private Integer statusValue;
    private String message;
    private String path;
    private String error;

}
