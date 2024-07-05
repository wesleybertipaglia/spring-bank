package com.wesleybertipaglia.bank.dtos;

import java.util.UUID;

import com.wesleybertipaglia.bank.enums.Roles;

public class UserDTO {
    private UUID id;
    private String name;
    private String username;
    private String email;
    private String password;
    private Roles role;

    public UserDTO() {
    }

    public UserDTO(String name, String username, String email, String password, Roles role) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserDTO(UUID id, String name, String username, String email, String password, Roles role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserDTO(UUID id, String name, String username, String email, Roles role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

}
