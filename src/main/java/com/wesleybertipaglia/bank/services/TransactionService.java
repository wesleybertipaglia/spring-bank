package com.wesleybertipaglia.bank.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wesleybertipaglia.bank.dtos.TransactionDTO;
import com.wesleybertipaglia.bank.models.Transaction;
import com.wesleybertipaglia.bank.models.TransactionType;
import com.wesleybertipaglia.bank.models.Account;
import com.wesleybertipaglia.bank.repositories.TransactionRepository;
import com.wesleybertipaglia.bank.repositories.AccountRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public List<TransactionDTO> listTransactions() {
        return transactionRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<TransactionDTO> getTransactionById(UUID id) {
        return transactionRepository.findById(id).map(this::convertToDTO);
    }

    public TransactionDTO createTransaction(UUID accountId, Transaction transaction) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        TransactionType type = transaction.getType();

        if (type == TransactionType.WITHDRAWAL) {
            if (account.getBalance() < transaction.getValue()) {
                throw new IllegalArgumentException("Insufficient funds");
            }
            account.setBalance(account.getBalance() - transaction.getValue());
        } else if (type == TransactionType.DEPOSIT) {
            account.setBalance(account.getBalance() + transaction.getValue());
        } else {
            throw new IllegalArgumentException("Invalid transaction type");
        }

        transaction.setAccount(account);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return convertToDTO(savedTransaction);
    }

    public Optional<TransactionDTO> updateTransaction(UUID id, Transaction transaction) {
        return transactionRepository.findById(id).map(existingTransaction -> {
            existingTransaction.setValue(transaction.getValue());
            existingTransaction.setType(transaction.getType());
            existingTransaction.setDescription(transaction.getDescription());
            existingTransaction.setAccount(transaction.getAccount());
            Transaction updatedTransaction = transactionRepository.save(existingTransaction);
            return convertToDTO(updatedTransaction);
        });
    }

    public void deleteTransaction(UUID id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Transaction not found");
        }
    }

    private TransactionDTO convertToDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setAccountId(transaction.getAccount().getId());
        dto.setValue(transaction.getValue());
        dto.setType(transaction.getType().toString());
        dto.setDescription(transaction.getDescription());
        return dto;
    }
}
