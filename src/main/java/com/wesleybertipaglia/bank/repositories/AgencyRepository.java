package com.wesleybertipaglia.bank.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wesleybertipaglia.bank.models.Agency;

public interface AgencyRepository extends JpaRepository<Agency, UUID> {

    boolean existsByNumberAndBankId(int number, UUID bankId);

}
