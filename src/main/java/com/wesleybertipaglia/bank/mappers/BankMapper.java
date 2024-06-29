package com.wesleybertipaglia.bank.mappers;

import com.wesleybertipaglia.bank.dtos.BankDTO;
import com.wesleybertipaglia.bank.models.Bank;

public class BankMapper {

    public static BankDTO toDTO(Bank bank) {
        return new BankDTO(bank.getId(), bank.getName(), bank.getCode());
    }

    public static Bank toEntity(BankDTO dto) {
        Bank bank = new Bank();
        bank.setId(dto.getId());
        bank.setName(dto.getName());
        bank.setCode(dto.getCode());
        return bank;
    }
}
