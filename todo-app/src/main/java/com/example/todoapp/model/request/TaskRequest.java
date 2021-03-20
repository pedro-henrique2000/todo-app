package com.example.todoapp.model.request;

import com.example.todoapp.model.Priority;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class TaskRequest {

    @Size(max = 25)
    @NotBlank(message = "Not valid description!")
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(required = true, example = "2021-08-20")
    private LocalDate conclusionPrevision;

    private Priority priority;

}
