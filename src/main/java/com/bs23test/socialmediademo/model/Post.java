package com.bs23test.socialmediademo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private String status;

    @Column(name = "location_id")
    private Long locationId;

    @ManyToOne
    @JoinColumn(name = "location_id",insertable = false,updatable = false)
    private Location location;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private PrivacyType privacyType;





}
