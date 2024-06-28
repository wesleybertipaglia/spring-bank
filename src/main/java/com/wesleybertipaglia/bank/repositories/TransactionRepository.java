package com.wesleybertipaglia.bank.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wesleybertipaglia.bank.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
