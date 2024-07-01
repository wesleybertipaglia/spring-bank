package com.wesleybertipaglia.bank.mappers;

import com.wesleybertipaglia.bank.dtos.TransactionDTO;
import com.wesleybertipaglia.bank.enums.TransactionType;
import com.wesleybertipaglia.bank.enums.TransactionStatus;
import com.wesleybertipaglia.bank.models.Transaction;

public class TransactionMapper {

    public static TransactionDTO convertToDTO(Transaction transaction) {
        if (transaction == null) {
            return null;
        } else if (transaction.getId() == null) {
            throw new IllegalArgumentException("Transaction ID is required");
        } else if (transaction.getAccount() == null) {
            throw new IllegalArgumentException("Transaction account is required");
        } else if (transaction.getValue() == 0) {
            throw new IllegalArgumentException("Transaction value is required");
        } else if (transaction.getType() == null) {
            throw new IllegalArgumentException("Transaction type is required");
        } else if (transaction.getStatus() == null) {
            throw new IllegalArgumentException("Transaction status is required");
        } else if (transaction.getDescription() == null) {
            throw new IllegalArgumentException("Transaction description is required");
        }

        return new TransactionDTO(transaction.getId(), transaction.getAccount().getId(), transaction.getValue(),
                transaction.getType().toString(), transaction.getDescription(), transaction.getStatus().toString());
    }

    public static Transaction convertToEntity(TransactionDTO transactionDTO) {
        if (transactionDTO == null) {
            return null;
        } else if (transactionDTO.getId() == null) {
            throw new IllegalArgumentException("Transaction ID is required");
        } else if (transactionDTO.getAccountId() == null) {
            throw new IllegalArgumentException("Transaction account is required");
        } else if (transactionDTO.getValue() == 0) {
            throw new IllegalArgumentException("Transaction value is required");
        } else if (transactionDTO.getType() == null) {
            throw new IllegalArgumentException("Transaction type is required");
        } else if (transactionDTO.getStatus() == null) {
            throw new IllegalArgumentException("Transaction status is required");
        } else if (transactionDTO.getDescription() == null) {
            throw new IllegalArgumentException("Transaction description is required");
        }

        return new Transaction(transactionDTO.getId(), transactionDTO.getValue(),
                TransactionType.valueOf(transactionDTO.getType()),
                TransactionStatus.valueOf(transactionDTO.getStatus()),
                transactionDTO.getDescription());
    }

}
