package com.wesleybertipaglia.bank.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wesleybertipaglia.bank.dtos.TransferDTO;
import com.wesleybertipaglia.bank.enums.TransactionStatus;
import com.wesleybertipaglia.bank.services.TransferService;

@RestController
@RequestMapping("/api/v1/transfers")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping("/")
    public ResponseEntity<TransferDTO> createTransfer(@RequestBody TransferDTO transferDTO) {
        return ResponseEntity.of(transferService.createTransfer(transferDTO));
    }

    @GetMapping("/")
    public ResponseEntity<Page<TransferDTO>> listTransfers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(transferService.listTransfers(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferDTO> getTransferById(@PathVariable UUID id) {
        return ResponseEntity.of(transferService.getTransferById(id));
    }

    @PutMapping("/{id}/{status}")
    public ResponseEntity<TransferDTO> updateTransferStatus(@PathVariable UUID id,
            @PathVariable TransactionStatus status) {
        return ResponseEntity.of(transferService.updateTransferStatus(id, status));
    }

}
