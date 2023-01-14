package com.sanedge.simpleblog.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class User {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "bio", nullable = false)
    private String bio;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}
