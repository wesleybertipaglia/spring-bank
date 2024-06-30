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

    @Transactional
    public Optional<BankDTO> createBank(BankDTO bankDTO) {
        if (bankRepository.existsByCode(bankDTO.getCode())) {
            throw new EntityExistsException("Bank code already exists");
        }

        Bank bank = new Bank(bankDTO.getName(), bankDTO.getCode());
        return Optional.of(BankMapper.convertToDTO(bankRepository.save(bank)));
    }

    @Transactional(readOnly = true)
    public Page<BankDTO> listBanks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bankRepository.findAll(pageable).map(BankMapper::convertToDTO);
    }

    @Transactional(readOnly = true)
    public Optional<BankDTO> getBankById(UUID id) {
        if (!bankRepository.existsById(id)) {
            throw new EntityNotFoundException("Bank not found");
        }

        return Optional.of(BankMapper.convertToDTO(bankRepository.findById(id).get()));
    }

    @Transactional
    public Optional<BankDTO> updateBank(UUID id, BankDTO bankDTO) {
        if (bankRepository.existsByCode(bankDTO.getCode())) {
            throw new EntityExistsException("Bank code already exists");
        }

        Bank storedBank = bankRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bank not found"));

        storedBank.setName(bankDTO.getName());
        storedBank.setCode(bankDTO.getCode());

        return Optional.of(BankMapper.convertToDTO(bankRepository.save(storedBank)));
    }

    @Transactional
    public Optional<String> deleteBank(UUID id) {
        Bank storedBank = bankRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bank not found"));

        bankRepository.delete(storedBank);
        return Optional.of("Bank deleted successfully");
    }

}
