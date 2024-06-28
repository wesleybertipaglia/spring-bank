package com.wesleybertipaglia.bank.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wesleybertipaglia.bank.dtos.BankDTO;
import com.wesleybertipaglia.bank.models.Bank;
import com.wesleybertipaglia.bank.repositories.BankRepository;

@Service
public class BankService {
    @Autowired
    private BankRepository bankRepository;

    public List<BankDTO> listBanks() {
        return bankRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<BankDTO> getBankById(UUID id) {
        return bankRepository.findById(id).map(this::convertToDTO);
    }

    public BankDTO createBank(Bank bank) {
        Bank savedBank = bankRepository.save(bank);
        return convertToDTO(savedBank);
    }

    public Optional<BankDTO> updateBank(UUID id, Bank bank) {
        return bankRepository.findById(id).map(existingBank -> {
            existingBank.setName(bank.getName());
            existingBank.setCode(bank.getCode());
            Bank updatedBank = bankRepository.save(existingBank);
            return convertToDTO(updatedBank);
        });
    }

    public void deleteBank(UUID id) {
        if (bankRepository.existsById(id)) {
            bankRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Bank not found");
        }
    }

    private BankDTO convertToDTO(Bank bank) {
        BankDTO dto = new BankDTO();
        dto.setId(bank.getId());
        dto.setName(bank.getName());
        dto.setCode(bank.getCode());
        return dto;
    }
}
