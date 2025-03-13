package org.example.sportflow.Model;

import java.time.LocalDateTime;

public class User {
    protected int id;
    protected String username;
    protected String password;
    protected Role role;

    public User(int id, String username, String password, Role role) {
    }

    public void setDateCreated(LocalDateTime dateCreated) {
    }

    public enum Role {
        admin , membre , entraineur
    }

    // Constructors
    public User() {
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "', role=" + role + "}";
    }
}