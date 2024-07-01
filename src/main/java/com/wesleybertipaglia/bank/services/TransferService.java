package com.wesleybertipaglia.bank.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wesleybertipaglia.bank.dtos.TransferDTO;
import com.wesleybertipaglia.bank.enums.TransactionStatus;
import com.wesleybertipaglia.bank.enums.TransactionType;
import com.wesleybertipaglia.bank.mappers.TransferMapper;
import com.wesleybertipaglia.bank.models.Transfer;
import com.wesleybertipaglia.bank.models.Account;
import com.wesleybertipaglia.bank.repositories.TransferRepository;

import jakarta.persistence.EntityNotFoundException;

import com.wesleybertipaglia.bank.repositories.AccountRepository;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public Optional<TransferDTO> createTransfer(TransferDTO transferDTO) {
        Account accountSource = accountRepository.findById(transferDTO.getAccountSourceId())
                .orElseThrow(() -> new IllegalArgumentException("Source account not found"));

        Account accountDestination = accountRepository.findById(transferDTO.getAccountDestinationId())
                .orElseThrow(() -> new IllegalArgumentException("Destination account not found"));

        TransactionType type = TransactionType.TRANSFER;
        TransactionStatus status = TransactionStatus.valueOf(transferDTO.getStatus());

        if (accountSource.getBalance() < transferDTO.getValue()) {
            throw new IllegalArgumentException("Insufficient balance");
        } else if (accountSource.getId().equals(accountDestination.getId())) {
            throw new IllegalArgumentException("Source and destination accounts must be different");
        }

        if (status.equals(TransactionStatus.COMPLETED)) {
            commitTransfer(accountSource, accountDestination, transferDTO.getValue());
        }

        Transfer transfer = new Transfer(accountSource, accountDestination, transferDTO.getValue(), type, status);
        return Optional.of(TransferMapper.convertToDTO(transferRepository.save(transfer)));
    }

    @Transactional(readOnly = true)
    public Page<TransferDTO> listTransfers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return transferRepository.findAll(pageable).map(TransferMapper::convertToDTO);
    }

    @Transactional(readOnly = true)
    public Optional<TransferDTO> getTransferById(UUID id) {
        if (!transferRepository.existsById(id)) {
            throw new EntityNotFoundException("Transfer not found");
        }

        return Optional.of(TransferMapper.convertToDTO(transferRepository.findById(id).get()));
    }

    @Transactional
    public Optional<TransferDTO> updateTransferStatus(UUID id, TransactionStatus newStatus) {
        Transfer transfer = transferRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transfer not found"));

        if (transfer.getStatus() != TransactionStatus.PENDING) {
            throw new IllegalArgumentException("Only pending transfers can be updated");
        } else if (newStatus == TransactionStatus.COMPLETED) {
            commitTransfer(transfer.getAccountSource(), transfer.getAccountDestination(), transfer.getValue());
        }

        transfer.setStatus(newStatus);
        transferRepository.save(transfer);

        return Optional.of(TransferMapper.convertToDTO(transfer));
    }

    @Transactional
    private void commitTransfer(Account accountSource, Account accountDestination, float value) {
        accountSource.setBalance(accountSource.getBalance() - value);
        accountDestination.setBalance(accountDestination.getBalance() + value);

        accountRepository.save(accountSource);
        accountRepository.save(accountDestination);
    }
}
