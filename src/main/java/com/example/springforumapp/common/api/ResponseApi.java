package com.example.springforumapp.common.api;


import lombok.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class ResponseApi {
    private ResponseStatusApi status;
    private int code;
}
