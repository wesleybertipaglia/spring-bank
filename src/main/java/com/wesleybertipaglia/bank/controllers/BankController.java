package com.wesleybertipaglia.bank.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wesleybertipaglia.bank.models.Bank;
import com.wesleybertipaglia.bank.services.BankService;

@RestController
@RequestMapping("/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping
    public List<Bank> listBanks() {
        return bankService.listBanks();
    }

    @GetMapping("/{id}")
    public Optional<Bank> getBankById(@PathVariable UUID id) {
        return bankService.getBankById(id);
    }

    @PostMapping
    public Bank createBank(@RequestBody Bank bank) {
        return bankService.createBank(bank);
    }

    @PutMapping("/{id}")
    public Optional<Bank> updateBank(@PathVariable UUID id, @RequestBody Bank bank) {
        return bankService.updateBank(id, bank);
    }

    @DeleteMapping("/{id}")
    public void deleteBank(@PathVariable UUID id) {
        bankService.deleteBank(id);
    }
}
