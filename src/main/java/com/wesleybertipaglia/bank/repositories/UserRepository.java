package com.wesleybertipaglia.bank.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wesleybertipaglia.bank.models.Account;
import com.wesleybertipaglia.bank.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByAccount(Account account);

}
