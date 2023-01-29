package com.example.springforumapp.common.api;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResponseSuccessApi extends ResponseApi{
    private Object body;

    public ResponseSuccessApi(ResponseStatusApi status, Object body) {
        super(status);
        this.body = body;
    }
}
