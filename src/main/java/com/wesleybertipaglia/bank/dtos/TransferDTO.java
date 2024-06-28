package com.wesleybertipaglia.bank.dtos;

import java.util.UUID;

public class TransferDTO {
    private UUID id;
    private UUID accountSourceId;
    private UUID accountDestinationId;
    private float value;

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
}
