package com.wesleybertipaglia.bank.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wesleybertipaglia.bank.dtos.BankDTO;
import com.wesleybertipaglia.bank.models.Bank;
import com.wesleybertipaglia.bank.services.BankService;

@RestController
@RequestMapping("/api/v1/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping
    public ResponseEntity<List<BankDTO>> listBanks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<BankDTO> banks = bankService.listBanks(page, size);
        return ResponseEntity.ok(banks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankDTO> getBankById(@PathVariable UUID id) {
        Optional<BankDTO> bank = bankService.getBankById(id);
        return bank.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BankDTO> createBank(@RequestBody Bank bank) {
        try {
            BankDTO createdBank = bankService.createBank(bank);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBank);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankDTO> updateBank(@PathVariable UUID id, @RequestBody Bank bank) {
        try {
            BankDTO updatedBank = bankService.updateBank(id, bank);
            return ResponseEntity.ok(updatedBank);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBank(@PathVariable UUID id) {
        try {
            bankService.deleteBank(id);
            return ResponseEntity.ok("Bank deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}