package com.wesleybertipaglia.bank.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wesleybertipaglia.bank.dtos.TransferDTO;
import com.wesleybertipaglia.bank.models.Transfer;
import com.wesleybertipaglia.bank.models.Account;
import com.wesleybertipaglia.bank.repositories.TransferRepository;
import com.wesleybertipaglia.bank.repositories.AccountRepository;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private AccountRepository accountRepository;

    public List<TransferDTO> listTransfers() {
        return transferRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<TransferDTO> getTransferById(UUID id) {
        return transferRepository.findById(id).map(this::convertToDTO);
    }

    @Transactional
    public TransferDTO createTransfer(UUID accountSourceId, UUID accountDestinationId, Transfer transfer) {
        Account accountSource = accountRepository.findById(accountSourceId)
                .orElseThrow(() -> new IllegalArgumentException("Source account not found"));

        Account accountDestination = accountRepository.findById(accountDestinationId)
                .orElseThrow(() -> new IllegalArgumentException("Destination account not found"));

        if (accountSource.getBalance() < transfer.getValue()) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        accountSource.setBalance(accountSource.getBalance() - transfer.getValue());
        accountDestination.setBalance(accountDestination.getBalance() + transfer.getValue());

        accountRepository.save(accountSource);
        accountRepository.save(accountDestination);

        transfer.setAccountSource(accountSource);
        transfer.setAccountDestination(accountDestination);
        Transfer savedTransfer = transferRepository.save(transfer);

        return convertToDTO(savedTransfer);
    }

    private TransferDTO convertToDTO(Transfer transfer) {
        TransferDTO dto = new TransferDTO();
        dto.setId(transfer.getId());
        dto.setAccountSourceId(transfer.getAccountSource().getId());
        dto.setAccountDestinationId(transfer.getAccountDestination().getId());
        dto.setValue(transfer.getValue());
        return dto;
    }
}
