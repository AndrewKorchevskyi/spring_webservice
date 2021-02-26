package com.simplewebservice.posts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.simplewebservice.users.User;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

@Entity
@ApiModel(description = "Post Details")
public class Post {

    @Id
    @GeneratedValue
    private int id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public Post() {}

    public Post(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "Posts{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
