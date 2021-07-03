package com.bs23test.socialmediademo.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseError extends ApiResponse {
    private Object error;

    public ApiResponseError() {
        super();
    }

    public ApiResponseError(String error) {
        super();
        this.error = error;
    }

    public ApiResponseError(Boolean status, Integer code, String response) {
        super(status, code, response);
    }

    public ApiResponseError(Boolean status, Integer code, String response, Object error) {
        super(status, code, response);
        this.error = error;
    }
}
