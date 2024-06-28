package com.wesleybertipaglia.bank.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wesleybertipaglia.bank.repositories.BankRepository;
import com.wesleybertipaglia.bank.models.Bank;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public List<Bank> listBanks() {
        return bankRepository.findAll();
    }

    public Optional<Bank> getBankById(UUID id) {
        return bankRepository.findById(id);
    }

    public Bank createBank(Bank bank) {
        return bankRepository.save(bank);
    }

    public Optional<Bank> updateBank(UUID id, Bank bank) {
        Optional<Bank> bankOptional = bankRepository.findById(id);

        if (bankOptional.isPresent()) {
            Bank bankToUpdate = bankOptional.get();
            bankToUpdate.setName(bank.getName());
            bankToUpdate.setCode(bank.getCode());

            return Optional.of(bankRepository.save(bankToUpdate));
        }

        return Optional.empty();
    }

    public void deleteBank(UUID id) {
        bankRepository.deleteById(id);
    }
}
