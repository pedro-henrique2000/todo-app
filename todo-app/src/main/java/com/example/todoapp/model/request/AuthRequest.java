package com.example.todoapp.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthRequest {
    @NotBlank(message = "not valid email")
    private String email;
    @NotBlank(message = "not valid password")
    private String password;

}
