package com.wesleybertipaglia.bank.dtos;

import java.util.UUID;

public class CustomerDTO {
    private UUID id;
    private UUID accountId;
    private String name;
    private String email;
    private String password;

    public CustomerDTO() {
    }

    public CustomerDTO(UUID accountId, String name, String email, String password) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public CustomerDTO(UUID id, UUID accountId, String name, String email, String password) {
        this.id = id;
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public CustomerDTO(UUID id, UUID accountId, String name, String email) {
        this.id = id;
        this.accountId = accountId;
        this.name = name;
        this.email = email;
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

}
