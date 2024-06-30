package com.wesleybertipaglia.bank.mappers;

import com.wesleybertipaglia.bank.dtos.AccountDTO;
import com.wesleybertipaglia.bank.models.Account;

public class AccountMapper {

    public static AccountDTO convertToDTO(Account account) {
        if (account == null) {
            return null;
        } else if (account.getId() == null) {
            throw new IllegalArgumentException("Account ID is required");
        } else if (account.getAgency() == null) {
            throw new IllegalArgumentException("Account agency is required");
        } else if (account.getNumber() == 0) {
            throw new IllegalArgumentException("Account number is required");
        }

        return new AccountDTO(account.getId(), account.getAgency().getId(), account.getNumber(), account.getBalance());
    }

    public static Account convertToEntity(AccountDTO accountDTO) {
        if (accountDTO == null) {
            return null;
        } else if (accountDTO.getId() == null) {
            throw new IllegalArgumentException("Account ID is required");
        } else if (accountDTO.getAgencyId() == null) {
            throw new IllegalArgumentException("Account agency is required");
        } else if (accountDTO.getNumber() == 0) {
            throw new IllegalArgumentException("Account number is required");
        }

        return new Account(accountDTO.getId(), accountDTO.getNumber(), accountDTO.getBalance());
    }

}
