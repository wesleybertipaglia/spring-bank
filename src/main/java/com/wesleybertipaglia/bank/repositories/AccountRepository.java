package com.wesleybertipaglia.bank.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wesleybertipaglia.bank.models.Account;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Account findByNumber(int number);

    boolean existsByNumber(int number);
}
