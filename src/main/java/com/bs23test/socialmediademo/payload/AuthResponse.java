package com.bs23test.socialmediademo.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {


    private String access_token;
    private Long expires_in;
    private String token_type;

    public AuthResponse() {
    }

    public AuthResponse(String access_token, Long expires_in, String token_type) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.token_type = token_type;
    }
}