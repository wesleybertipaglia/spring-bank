package com.wesleybertipaglia.bank.dtos;

import java.util.UUID;

public class AccountDTO {
    private UUID id;
    private UUID agencyId;
    private int number;
    private float balance;

    public AccountDTO() {
    }

    public AccountDTO(UUID agencyId, int number, float balance) {
        this.agencyId = agencyId;
        this.number = number;
        this.balance = balance;
    }

    public AccountDTO(UUID id, UUID agencyId, int number, float balance) {
        this.id = id;
        this.agencyId = agencyId;
        this.number = number;
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(UUID agencyId) {
        this.agencyId = agencyId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

}
