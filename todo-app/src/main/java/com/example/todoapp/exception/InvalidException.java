package com.example.todoapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidException extends RuntimeException {

    public InvalidException(String msg) {
        super(msg);
    }

    public InvalidException() {
        super("Invalid Exception");
    }

}
