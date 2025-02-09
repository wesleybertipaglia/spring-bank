package com.wesleybertipaglia.bank.dtos;

import java.util.UUID;

public class AgencyDTO {

    private UUID id;
    private UUID bankId;
    private int number;
    private String address;

    public AgencyDTO() {
    }

    public AgencyDTO(UUID id, UUID bankId, int number, String address) {
        this.id = id;
        this.bankId = bankId;
        this.number = number;
        this.address = address;
    }

    public AgencyDTO(UUID bankId, int number, String address) {
        this.bankId = bankId;
        this.number = number;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getBankId() {
        return bankId;
    }

    public void setBankId(UUID bankId) {
        this.bankId = bankId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
