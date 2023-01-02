package com.example.springforumapp.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiSuccess {
    private ApiStatus status;
    private int code;
    private Object body;
}
