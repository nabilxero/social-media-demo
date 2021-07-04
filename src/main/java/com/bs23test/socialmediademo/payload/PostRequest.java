package com.bs23test.socialmediademo.payload;

import com.bs23test.socialmediademo.model.PrivacyType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {
    private String postStatus;
    private PrivacyType privacyType;
    private String locationId;

    public PostRequest() {
    }

    public PostRequest(String postStatus, PrivacyType privacyType, String locationId) {
        this.postStatus = postStatus;
        this.privacyType = privacyType;
        this.locationId = locationId;
    }
}
