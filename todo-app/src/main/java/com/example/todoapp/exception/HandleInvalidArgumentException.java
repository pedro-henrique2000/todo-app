package com.example.todoapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class HandleInvalidArgumentException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handle(
            HttpServletRequest req, MethodArgumentNotValidException ex
    ) {

        InvalidDataError invalidError = new InvalidDataError();
        HttpStatus myStatus = HttpStatus.BAD_REQUEST;

        invalidError.setPath(req.getRequestURI());
        invalidError.setTimestamp(LocalDateTime.now());
        invalidError.setError("Validation Error");
        invalidError.setMessage(ex.getBindingResult().getAllErrors()
                .stream()
                .findFirst()
                .get()
                .getDefaultMessage());
        invalidError.setStatusValue(myStatus.value());

        return ResponseEntity.status(myStatus).body(invalidError);


    }

}
