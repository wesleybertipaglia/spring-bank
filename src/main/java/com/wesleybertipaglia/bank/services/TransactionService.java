package com.wesleybertipaglia.bank.services;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wesleybertipaglia.bank.dtos.TransactionDTO;
import com.wesleybertipaglia.bank.enums.TransactionStatus;
import com.wesleybertipaglia.bank.enums.TransactionType;
import com.wesleybertipaglia.bank.mappers.TransactionMapper;
import com.wesleybertipaglia.bank.models.Transaction;
import com.wesleybertipaglia.bank.models.Account;
import com.wesleybertipaglia.bank.repositories.TransactionRepository;

import jakarta.persistence.EntityNotFoundException;

import com.wesleybertipaglia.bank.repositories.AccountRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public Optional<TransactionDTO> createTransaction(TransactionDTO transactionDTO) {
        Account account = accountRepository.findById(transactionDTO.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        TransactionType type = TransactionType.valueOf(transactionDTO.getType());
        TransactionStatus status = TransactionStatus.valueOf(transactionDTO.getStatus());

        Transaction transaction = new Transaction(transactionDTO.getValue(), type, status,
                transactionDTO.getDescription(), account);
        return Optional.of(TransactionMapper.convertToDTO(transactionRepository.save(transaction)));
    }

    @Transactional(readOnly = true)
    public Page<TransactionDTO> listTransactions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return transactionRepository.findAll(pageable).map(TransactionMapper::convertToDTO);
    }

    @Transactional(readOnly = true)
    public Optional<TransactionDTO> getTransactionById(UUID id) {
        if (!transactionRepository.existsById(id)) {
            throw new EntityNotFoundException("Transaction not found");
        }

        return Optional.of(TransactionMapper.convertToDTO(transactionRepository.findById(id).get()));
    }

    @Transactional
    public Optional<TransactionDTO> updateTransactionStatus(UUID id, TransactionStatus newStatus) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found"));

        if (transaction.getStatus() != TransactionStatus.PENDING) {
            throw new IllegalArgumentException("Only pending transactions can be updated");
        } else if (newStatus == TransactionStatus.COMPLETED) {
            commitTransaction(transaction);
        }

        transaction.setStatus(newStatus);
        transactionRepository.save(transaction);

        return Optional.of(TransactionMapper.convertToDTO(transaction));
    }

    @Transactional
    private void commitTransaction(Transaction transaction) {
        Account account = transaction.getAccount();
        if (transaction.getType() == TransactionType.WITHDRAWAL) {
            account.setBalance(account.getBalance() - transaction.getValue());
        } else if (transaction.getType() == TransactionType.DEPOSIT) {
            account.setBalance(account.getBalance() + transaction.getValue());
        }
        accountRepository.save(account);
    }

}
