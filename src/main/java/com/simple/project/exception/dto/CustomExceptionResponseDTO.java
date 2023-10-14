package com.simple.project.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomExceptionResponseDTO {

    private Integer status;
    private String title;
    private String url;
    private String message;
}
