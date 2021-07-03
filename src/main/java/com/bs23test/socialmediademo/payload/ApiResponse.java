package com.bs23test.socialmediademo.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    private Boolean status;
    private Integer code;
    private String response;
    private Object data;

    public ApiResponse() {
    }

    public ApiResponse(Boolean status, String response) {
        this.status = status;
        this.response = response;
    }

    public ApiResponse(Boolean status, String response, Object data) {
        this.status = status;
        this.response = response;
        this.data=data;
    }

    public ApiResponse(Boolean status, Integer code, String response) {
        this.status = status;
        this.code = code;
        this.response = response;
    }

    public ApiResponse(Boolean status, Integer code, String response, Object object) {
        this.status = status;
        this.code = code;
        this.response = response;
        this.data = object;
    }
}
