package com.example.springforumapp.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private ApiStatus status;
    private int code;
    private String message;
    private String dbgMessage;
    private long timestamp;
}
