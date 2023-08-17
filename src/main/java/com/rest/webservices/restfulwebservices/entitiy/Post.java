package com.rest.webservices.restfulwebservices.entitiy;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import javax.annotation.processing.Generated;

@Entity
public class Post {
    @Id
    @GeneratedValue
    @JoinColumn
    private Integer id;
    @Size(min = 5, message = "Name must have at least 5 characters")
    @JoinColumn
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    public Post() {
    }

    public Post(Integer id, String description) {
        this.id = id;
        this.description = description;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
