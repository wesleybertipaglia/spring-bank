package com.wesleybertipaglia.bank.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @PostMapping
    public ResponseEntity<BankDTO> createBank(@RequestBody Bank bank) {
        return ResponseEntity.of(bankService.createBank(bank));
    }

    @GetMapping
    public ResponseEntity<Page<BankDTO>> listBanks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(bankService.listBanks(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankDTO> getBankById(@PathVariable UUID id) {
        return ResponseEntity.of(bankService.getBankById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankDTO> updateBank(@PathVariable UUID id, @RequestBody Bank bank) {
        return ResponseEntity.of(bankService.updateBank(id, bank));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBank(@PathVariable UUID id) {
        return ResponseEntity.of(bankService.deleteBank(id));
    }

}