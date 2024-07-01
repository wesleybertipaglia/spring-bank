package com.wesleybertipaglia.bank.dtos;

import java.util.UUID;

public class TransferDTO {
    private UUID id;
    private UUID accountSourceId;
    private UUID accountDestinationId;
    private float value;
    private String type;
    private String status;

    public TransferDTO() {
        this.type = "TRANSFER";
        this.status = "PENDING";
    }

    public TransferDTO(UUID accountSourceId, UUID accountDestinationId, float value) {
        this.accountSourceId = accountSourceId;
        this.accountDestinationId = accountDestinationId;
        this.value = value;
        this.type = "TRANSFER";
        this.status = "PENDING";
    }

    public TransferDTO(UUID id, UUID accountSourceId, UUID accountDestinationId, float value, String status) {
        this.id = id;
        this.accountSourceId = accountSourceId;
        this.accountDestinationId = accountDestinationId;
        this.value = value;
        this.type = "TRANSFER";
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAccountSourceId() {
        return accountSourceId;
    }

    public void setAccountSourceId(UUID accountSourceId) {
        this.accountSourceId = accountSourceId;
    }

    public UUID getAccountDestinationId() {
        return accountDestinationId;
    }

    public void setAccountDestinationId(UUID accountDestinationId) {
        this.accountDestinationId = accountDestinationId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
