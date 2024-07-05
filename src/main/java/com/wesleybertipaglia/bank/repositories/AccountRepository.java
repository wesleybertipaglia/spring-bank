package com.wesleybertipaglia.bank.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wesleybertipaglia.bank.models.Account;
import com.wesleybertipaglia.bank.models.User;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    Account findByNumber(int number);

    boolean existsByNumber(int number);

    User findByUserId(UUID userId);

    boolean existsByUserId(UUID userId);

    User findByUser(User user);

    boolean existsByUser(User user);
}
