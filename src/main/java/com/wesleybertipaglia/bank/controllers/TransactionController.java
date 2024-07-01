package com.wesleybertipaglia.bank.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wesleybertipaglia.bank.dtos.TransactionDTO;
import com.wesleybertipaglia.bank.enums.TransactionStatus;
import com.wesleybertipaglia.bank.services.TransactionService;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/")
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.of(transactionService.createTransaction(transactionDTO));
    }

    @GetMapping("/")
    public ResponseEntity<Page<TransactionDTO>> listTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(transactionService.listTransactions(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable UUID id) {
        return ResponseEntity.of(transactionService.getTransactionById(id));
    }

    @PutMapping("/{id}/{status}")
    public ResponseEntity<TransactionDTO> updateTransactionStatus(@PathVariable UUID id,
            @PathVariable TransactionStatus status) {
        return ResponseEntity.of(transactionService.updateTransactionStatus(id, status));
    }

}
