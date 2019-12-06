package com.itla.postapp.model.user;

public class User {

    private int createdAt;
    private String email;
    private String password;
    private int id;
    private String name;
    private int posts;

    public User(Integer createdAt, String email, String password, Integer id, String name, Integer posts) {
        this.createdAt = createdAt;
        this.email = email;
        this.password = password;
        this.id = id;
        this.name = name;
        this.posts = posts;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosts() {
        return posts;
    }

    public void setPosts(int posts) {
        this.posts = posts;
    }
}
