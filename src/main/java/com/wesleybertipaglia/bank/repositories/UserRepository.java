package com.wesleybertipaglia.bank.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wesleybertipaglia.bank.models.Account;
import com.wesleybertipaglia.bank.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email);

    User findByUsername(String username);

    User findByAccount(Account account);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByAccount(Account account);

}
