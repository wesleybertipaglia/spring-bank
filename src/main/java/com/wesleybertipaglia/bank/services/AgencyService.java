package com.wesleybertipaglia.bank.services;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wesleybertipaglia.bank.dtos.AgencyDTO;
import com.wesleybertipaglia.bank.mappers.AgencyMapper;
import com.wesleybertipaglia.bank.models.Agency;
import com.wesleybertipaglia.bank.models.Bank;
import com.wesleybertipaglia.bank.repositories.AgencyRepository;
import com.wesleybertipaglia.bank.repositories.BankRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AgencyService {

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private BankRepository bankRepository;

    @Transactional
    public Optional<AgencyDTO> createAgency(AgencyDTO agencyDTO) {
        if (agencyRepository.existsByNumberAndBankId(agencyDTO.getNumber(), agencyDTO.getBankId())) {
            throw new EntityExistsException("Agency number already exists");
        }

        Bank bank = bankRepository.findById(agencyDTO.getBankId())
                .orElseThrow(() -> new EntityNotFoundException("Bank not found"));

        Agency agency = new Agency(agencyDTO.getNumber(), agencyDTO.getAddress(), bank);
        return Optional.of(AgencyMapper.convertToDTO(agencyRepository.save(agency)));
    }

    @Transactional(readOnly = true)
    public Page<AgencyDTO> listAgencies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return agencyRepository.findAll(pageable).map(AgencyMapper::convertToDTO);
    }

    @Transactional(readOnly = true)
    public Optional<AgencyDTO> getAgencyById(UUID id) {
        if (!agencyRepository.existsById(id)) {
            throw new EntityNotFoundException("Agency not found");
        }

        return Optional.of(AgencyMapper.convertToDTO(agencyRepository.findById(id).get()));
    }

    @Transactional
    public Optional<AgencyDTO> updateAgency(UUID id, AgencyDTO agencyDTO) {
        Agency storedAgency = agencyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agency not found"));

        Bank bank = bankRepository.findById(agencyDTO.getBankId())
                .orElseThrow(() -> new EntityNotFoundException("Bank not found"));

        storedAgency.setNumber(agencyDTO.getNumber());
        storedAgency.setAddress(agencyDTO.getAddress());
        storedAgency.setBank(bank);

        return Optional.of(AgencyMapper.convertToDTO(agencyRepository.save(storedAgency)));
    }

    public Optional<String> deleteAgency(UUID id) {
        Agency storedAgency = agencyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agency not found"));

        agencyRepository.delete(storedAgency);
        return Optional.of("Agency deleted successfully");
    }

}
