package com.bs23test.socialmediademo.dto;

import com.bs23test.socialmediademo.model.Location;
import com.bs23test.socialmediademo.model.PrivacyType;
import com.bs23test.socialmediademo.model.User;
import lombok.Data;

@Data
public class PostDto {
    private String id;
    private String status;
    private Long locationId;
    private Location location;
    private Long userId;
    private User user;
    private PrivacyType privacyType;
}
