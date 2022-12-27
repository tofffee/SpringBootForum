package com.example.springforumapp.errors;

import org.springframework.http.HttpStatus;

public class ApiSuccess {
    private int status;
    private Object body;

    public ApiSuccess(int status, Object body) {
        this.status = status;
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
