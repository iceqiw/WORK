package com.cwgj.bigdata.api.data_provider.exceptionHandler;

import lombok.Data;

@Data
public class ExResponse {
    private int errorCode;
    private String message;
    private Long timestamp = System.currentTimeMillis();

    protected ExResponse(String message, int code) {
        this.errorCode = code;
        this.message = message;
    }

}
