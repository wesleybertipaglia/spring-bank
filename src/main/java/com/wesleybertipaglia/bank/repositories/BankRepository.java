package com.wesleybertipaglia.bank.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wesleybertipaglia.bank.models.Bank;

public interface BankRepository extends JpaRepository<Bank, UUID> {
    Bank findByCode(String code);

    boolean existsByCode(String code);
}
