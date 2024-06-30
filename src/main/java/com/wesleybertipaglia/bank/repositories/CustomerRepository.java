package com.wesleybertipaglia.bank.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wesleybertipaglia.bank.models.Account;
import com.wesleybertipaglia.bank.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Customer findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByAccount(Account account);

}
