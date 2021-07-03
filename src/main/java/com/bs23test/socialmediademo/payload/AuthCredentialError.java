package com.bs23test.socialmediademo.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthCredentialError {
    private String field;
    private String message;
}
