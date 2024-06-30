package com.wesleybertipaglia.bank.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wesleybertipaglia.bank.dtos.BankDTO;
import com.wesleybertipaglia.bank.mappers.BankMapper;
import com.wesleybertipaglia.bank.models.Bank;
import com.wesleybertipaglia.bank.repositories.BankRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    @Transactional(readOnly = true)
    public Page<BankDTO> listBanks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bankRepository.findAll(pageable).map(BankMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Optional<BankDTO> getBankById(UUID id) {
        if (!bankRepository.existsById(id)) {
            throw new EntityNotFoundException("Bank not found");
        }

        return Optional.of(BankMapper.toDTO(bankRepository.findById(id).get()));
    }

    @Transactional
    public Optional<BankDTO> createBank(Bank bank) {
        if (bankRepository.existsByCode(bank.getCode())) {
            throw new EntityExistsException("Bank code already exists");
        }

        return Optional.of(BankMapper.toDTO(bankRepository.save(bank)));
    }

    @Transactional
    public Optional<BankDTO> updateBank(UUID id, Bank bank) {
        Bank storedBank = bankRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bank not found"));

        storedBank.setName(bank.getName());
        storedBank.setCode(bank.getCode());

        return Optional.of(BankMapper.toDTO(bankRepository.save(storedBank)));
    }

    @Transactional
    public Optional<String> deleteBank(UUID id) {
        Bank storedBank = bankRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bank not found"));

        bankRepository.delete(storedBank);
        return Optional.of("Bank deleted successfully");
    }
}
