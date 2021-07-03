package com.bs23test.socialmediademo.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {
    private String postStatus;

    public PostRequest() {
    }

    public PostRequest(String postStatus) {
        this.postStatus = postStatus;
    }
}
