package com.wesleybertipaglia.bank.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wesleybertipaglia.bank.dtos.BankDTO;
import com.wesleybertipaglia.bank.models.Bank;
import com.wesleybertipaglia.bank.services.BankService;

@RestController
@RequestMapping("/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping
    public List<BankDTO> listBanks() {
        return bankService.listBanks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankDTO> getBankById(@PathVariable UUID id) {
        Optional<BankDTO> bank = bankService.getBankById(id);
        return bank.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public BankDTO createBank(@RequestBody Bank bank) {
        return bankService.createBank(bank);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankDTO> updateBank(@PathVariable UUID id, @RequestBody Bank bank) {
        Optional<BankDTO> updatedBank = bankService.updateBank(id, bank);
        return updatedBank.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBank(@PathVariable UUID id) {
        try {
            bankService.deleteBank(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
