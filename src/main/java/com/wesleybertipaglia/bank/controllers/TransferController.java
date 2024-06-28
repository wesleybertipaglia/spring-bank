package com.wesleybertipaglia.bank.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wesleybertipaglia.bank.dtos.TransferDTO;
import com.wesleybertipaglia.bank.models.Transfer;
import com.wesleybertipaglia.bank.services.TransferService;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping("/")
    public TransferDTO createTransfer(@RequestBody TransferDTO request) {
        Transfer transfer = new Transfer();
        transfer.setValue(request.getValue());
        return transferService.createTransfer(request.getAccountSourceId(), request.getAccountDestinationId(),
                transfer);
    }

    @GetMapping("/")
    public List<TransferDTO> listTransfers() {
        return transferService.listTransfers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferDTO> getTransferById(@PathVariable UUID id) {
        Optional<TransferDTO> transfer = transferService.getTransferById(id);
        return transfer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
