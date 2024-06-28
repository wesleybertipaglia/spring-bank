package com.wesleybertipaglia.bank.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wesleybertipaglia.bank.dtos.AccountDTO;
import com.wesleybertipaglia.bank.models.Account;
import com.wesleybertipaglia.bank.models.Agency;
import com.wesleybertipaglia.bank.repositories.AccountRepository;
import com.wesleybertipaglia.bank.repositories.AgencyRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AgencyRepository agencyRepository;

    public List<AccountDTO> listAccounts() {
        return accountRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<AccountDTO> getAccountById(UUID id) {
        return accountRepository.findById(id).map(this::convertToDTO);
    }

    public AccountDTO createAccount(UUID agencyId, Account account) {
        Agency agency = agencyRepository.findById(agencyId)
                .orElseThrow(() -> new IllegalArgumentException("Agency not found"));

        account.setAgency(agency);
        Account savedAccount = accountRepository.save(account);
        return convertToDTO(savedAccount);
    }

    public Optional<AccountDTO> updateAccount(UUID id, Account account) {
        return accountRepository.findById(id).map(existingAccount -> {
            existingAccount.setNumber(account.getNumber());
            existingAccount.setBalance(account.getBalance());
            existingAccount.setAgency(account.getAgency());
            Account updatedAccount = accountRepository.save(existingAccount);
            return convertToDTO(updatedAccount);
        });
    }

    public void deleteAccount(UUID id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Account not found");
        }
    }

    private AccountDTO convertToDTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setId(account.getId());
        dto.setAgencyId(account.getAgency().getId());
        dto.setNumber(account.getNumber());
        dto.setBalance(account.getBalance());
        return dto;
    }
}
