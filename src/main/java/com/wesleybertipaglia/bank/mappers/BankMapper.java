package com.wesleybertipaglia.bank.mappers;

import com.wesleybertipaglia.bank.dtos.BankDTO;
import com.wesleybertipaglia.bank.models.Bank;

public class BankMapper {
    public static BankDTO convertToDTO(Bank bank) {
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

    public static Bank convertToEntity(BankDTO dto) {
        if (dto == null) {
            return null;
        } else if (dto.getId() == null) {
            throw new IllegalArgumentException("Bank ID is required");
        } else if (dto.getName() == null) {
            throw new IllegalArgumentException("Bank name is required");
        } else if (dto.getCode() == null) {
            throw new IllegalArgumentException("Bank code is required");
        }

        return new Bank(dto.getId(), dto.getName(), dto.getCode());
    }
}
