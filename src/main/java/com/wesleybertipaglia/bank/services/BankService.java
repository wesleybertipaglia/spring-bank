package com.wesleybertipaglia.bank.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    @Transactional(readOnly = true)
    public List<BankDTO> listBanks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Bank> bankPage = bankRepository.findAll(pageable);
        return bankPage.getContent().stream()
                .map(BankMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<BankDTO> getBankById(UUID id) {
        return bankRepository.findById(id)
                .map(BankMapper::toDTO);
    }

    @Transactional
    public BankDTO createBank(Bank bank) {
        if (bankRepository.existsByCode(bank.getCode())) {
            throw new IllegalArgumentException("Bank code already exists");
        }

        return BankMapper.toDTO(bankRepository.save(bank));
    }

    @Transactional
    public BankDTO updateBank(UUID id, Bank bank) {
        Bank storedBank = bankRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bank not found"));

        storedBank.setName(bank.getName());
        storedBank.setCode(bank.getCode());

        return BankMapper.toDTO(bankRepository.save(storedBank));
    }

    @Transactional
    public void deleteBank(UUID id) {
        if (!bankRepository.existsById(id)) {
            throw new IllegalArgumentException("Bank not found");
        }

        bankRepository.deleteById(id);
    }
}
