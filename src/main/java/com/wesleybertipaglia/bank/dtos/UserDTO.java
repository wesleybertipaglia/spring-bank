package com.wesleybertipaglia.bank.dtos;

import java.util.UUID;

import com.wesleybertipaglia.bank.enums.Roles;

public class UserDTO {
    private UUID id;
    private UUID accountId;
    private String name;
    private String username;
    private String email;
    private String password;
    private Roles role;

    public UserDTO() {
    }

    public UserDTO(UUID accountId, String name, String username, String email, String password, Roles role) {
        this.accountId = accountId;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserDTO(UUID id, UUID accountId, String name, String username, String email, String password, Roles role) {
        this.id = id;
        this.accountId = accountId;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserDTO(UUID id, UUID accountId, String name, String username, String email, Roles role) {
        this.id = id;
        this.accountId = accountId;
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

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
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
