package com.wesleybertipaglia.bank.mappers;

import com.wesleybertipaglia.bank.dtos.BankDTO;
import com.wesleybertipaglia.bank.models.Bank;

public class BankMapper {
    public static BankDTO toDTO(Bank bank) {
        if (bank == null) {
            return null;
        } else if (bank.getId() == null) {
            throw new IllegalArgumentException("Bank ID is required");
        } else if (bank.getName() == null) {
            throw new IllegalArgumentException("Bank name is required");
        } else if (bank.getCode() == null) {
            throw new IllegalArgumentException("Bank code is required");
        }

        return new BankDTO(bank.getId(), bank.getName(), bank.getCode());
    }

    public static Bank toEntity(BankDTO dto) {
        if (dto == null) {
            return null;
        } else if (dto.getId() == null) {
            throw new IllegalArgumentException("Bank ID is required");
        } else if (dto.getName() == null) {
            throw new IllegalArgumentException("Bank name is required");
        } else if (dto.getCode() == null) {
            throw new IllegalArgumentException("Bank code is required");
        }

        Bank bank = new Bank();
        bank.setId(dto.getId());
        bank.setName(dto.getName());
        bank.setCode(dto.getCode());
        return bank;
    }
}
