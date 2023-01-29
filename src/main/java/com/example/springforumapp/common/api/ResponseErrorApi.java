package com.example.springforumapp.common.api;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResponseErrorApi extends ResponseApi{
    private String message;
    private String dbgMessage;
    private long timestamp;

    public ResponseErrorApi(ResponseStatusApi status, String message, String dbgMessage, long timestamp) {
        super(status);
        this.message = message;
        this.dbgMessage = dbgMessage;
        this.timestamp = timestamp;
    }
}
