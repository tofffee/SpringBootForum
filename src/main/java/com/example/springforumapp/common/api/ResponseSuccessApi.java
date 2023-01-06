package com.example.springforumapp.common.api;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResponseSuccessApi extends ResponseApi{
    private Object body;

    public ResponseSuccessApi(ResponseStatusApi status, int code, Object body) {
        super(status, code);
        this.body = body;
    }
}
