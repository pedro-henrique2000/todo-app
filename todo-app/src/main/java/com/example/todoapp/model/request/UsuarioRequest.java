package com.example.todoapp.model.request;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UsuarioRequest {

    @Email(message = "Not valid email")
    private String email;

    @NotBlank(message = "Not valid password")
    private String password;

    @NotBlank(message = "Not valid password")
    private String confirmPassword;

    @NotBlank(message = "Not valid name")
    @Size(min = 5, max = 20)
    private String name;

}
