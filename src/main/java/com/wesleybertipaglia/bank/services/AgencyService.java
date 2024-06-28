package com.wesleybertipaglia.bank.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wesleybertipaglia.bank.dtos.AgencyDTO;
import com.wesleybertipaglia.bank.models.Agency;
import com.wesleybertipaglia.bank.models.Bank;
import com.wesleybertipaglia.bank.repositories.AgencyRepository;
import com.wesleybertipaglia.bank.repositories.BankRepository;

@Service
public class AgencyService {
    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private BankRepository bankRepository;

    public List<AgencyDTO> listAgencies() {
        return agencyRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<AgencyDTO> getAgencyById(UUID id) {
        return agencyRepository.findById(id).map(this::convertToDTO);
    }

    public AgencyDTO createAgency(UUID bankId, Agency agency) {
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new IllegalArgumentException("Bank not found"));

        agency.setBank(bank);
        Agency savedAgency = agencyRepository.save(agency);
        return convertToDTO(savedAgency);
    }

    public Optional<AgencyDTO> updateAgency(UUID id, Agency agency) {
        return agencyRepository.findById(id).map(existingAgency -> {
            existingAgency.setNumber(agency.getNumber());
            existingAgency.setAddress(agency.getAddress());
            existingAgency.setBank(agency.getBank());
            Agency updatedAgency = agencyRepository.save(existingAgency);
            return convertToDTO(updatedAgency);
        });
    }

    public void deleteAgency(UUID id) {
        if (agencyRepository.existsById(id)) {
            agencyRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Agency not found");
        }
    }

    private AgencyDTO convertToDTO(Agency agency) {
        AgencyDTO dto = new AgencyDTO();
        dto.setId(agency.getId());
        dto.setNumber(agency.getNumber());
        dto.setAddress(agency.getAddress());
        dto.setBankId(agency.getBank().getId());
        return dto;
    }
}
