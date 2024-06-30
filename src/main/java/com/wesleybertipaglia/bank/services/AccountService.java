package com.wesleybertipaglia.bank.services;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wesleybertipaglia.bank.dtos.AccountDTO;
import com.wesleybertipaglia.bank.mappers.AccountMapper;
import com.wesleybertipaglia.bank.models.Account;
import com.wesleybertipaglia.bank.models.Agency;
import com.wesleybertipaglia.bank.repositories.AccountRepository;
import com.wesleybertipaglia.bank.repositories.AgencyRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AgencyRepository agencyRepository;

    @Transactional
    public Optional<AccountDTO> createAccount(AccountDTO accountDTO) {
        if (accountRepository.findByNumber(accountDTO.getNumber()) != null) {
            throw new EntityExistsException("Account number already exists");
        }

        Agency agency = agencyRepository.findById(accountDTO.getAgencyId())
                .orElseThrow(() -> new EntityNotFoundException("Agency not found"));

        Account account = new Account(agency, accountDTO.getNumber(), accountDTO.getBalance());
        return Optional.of(AccountMapper.convertToDTO(accountRepository.save(account)));
    }

    @Transactional(readOnly = true)
    public Page<AccountDTO> listAccounts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return accountRepository.findAll(pageable).map(AccountMapper::convertToDTO);
    }

    @Transactional(readOnly = true)
    public Optional<AccountDTO> getAccountById(UUID id) {
        if (!accountRepository.existsById(id)) {
            throw new EntityNotFoundException("Account not found");
        }

        return Optional.of(AccountMapper.convertToDTO(accountRepository.findById(id).get()));
    }

    @Transactional
    public Optional<AccountDTO> updateAccount(UUID id, AccountDTO accountDTO) {
        Account storedAccount = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        Agency agency = agencyRepository.findById(accountDTO.getAgencyId())
                .orElseThrow(() -> new EntityNotFoundException("Agency not found"));

        if (accountRepository.findByNumber(accountDTO.getNumber()) != null
                && storedAccount.getNumber() != accountDTO.getNumber()) {
            throw new EntityExistsException("Account number already exists");
        }

        storedAccount.setAgency(agency);
        storedAccount.setNumber(accountDTO.getNumber());
        storedAccount.setBalance(accountDTO.getBalance());

        return Optional.of(AccountMapper.convertToDTO(accountRepository.save(storedAccount)));
    }

    public Optional<String> deleteAccount(UUID id) {
        Account storedAccount = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        accountRepository.delete(storedAccount);
        return Optional.of("Account deleted successfully");
    }

}
