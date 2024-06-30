package com.wesleybertipaglia.bank.dtos;

import java.util.UUID;

public class BankDTO {
    private UUID id;
    private String name;
    private String code;

    public BankDTO() {
    }

    public BankDTO(UUID id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public BankDTO(String name, String code) {
        this.name = name;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
