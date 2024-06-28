package com.wesleybertipaglia.bank.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wesleybertipaglia.bank.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
