package com.wesleybertipaglia.bank.dtos;

import java.util.UUID;

public class TransactionDTO {
    private UUID id;
    private UUID accountId;
    private float value;
    private String type;
    private String description;
    private String status;

    public TransactionDTO() {
        this.status = "PENDING";
    }

    public TransactionDTO(UUID accountId, float value, String type, String description) {
        this.accountId = accountId;
        this.value = value;
        this.type = type;
        this.status = "PENDING";
        this.description = description;
    }

    public TransactionDTO(UUID accountId, float value, String type, String description, String status) {
        this.accountId = accountId;
        this.value = value;
        this.type = type;
        this.status = status;
        this.description = description;
    }

    public TransactionDTO(UUID id, UUID accountId, float value, String type, String description, String status) {
        this.id = id;
        this.accountId = accountId;
        this.value = value;
        this.type = type;
        this.status = status;
        this.description = description;
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

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
