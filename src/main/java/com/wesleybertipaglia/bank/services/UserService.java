package com.wesleybertipaglia.bank.services;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wesleybertipaglia.bank.dtos.UserDTO;
import com.wesleybertipaglia.bank.mappers.UserMapper;
import com.wesleybertipaglia.bank.models.User;
import com.wesleybertipaglia.bank.models.Account;
import com.wesleybertipaglia.bank.repositories.UserRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import com.wesleybertipaglia.bank.repositories.AccountRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public Optional<UserDTO> createUser(UserDTO customerDTO) {
        if (customerRepository.existsByEmail(customerDTO.getEmail())) {
            throw new EntityExistsException("User email already exists");
        }

        Account account = accountRepository.findById(customerDTO.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        if (customerRepository.existsByAccount(account)) {
            throw new EntityExistsException("Account already has a customer");
        }

        User customer = new User(customerDTO.getName(), customerDTO.getEmail(), customerDTO.getPassword(),
                account);
        return Optional.of(UserMapper.convertToDTO(customerRepository.save(customer)));
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> listUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepository.findAll(pageable).map(UserMapper::convertToDTO);
    }

    @Transactional(readOnly = true)
    public Optional<UserDTO> getUserById(UUID id) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }

        return Optional.of(UserMapper.convertToDTO(customerRepository.findById(id).get()));
    }

    @Transactional
    public Optional<UserDTO> updateUser(UUID id, UserDTO customerDTO) {
        User storedUser = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Account account = accountRepository.findById(customerDTO.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        if (customerRepository.existsByAccount(account)
                && storedUser.getAccount().getId() != customerDTO.getAccountId()) {
            throw new EntityExistsException("Account already has a customer");
        }

        if (customerRepository.existsByEmail(customerDTO.getEmail())
                && storedUser.getEmail() != customerDTO.getEmail()) {
            throw new EntityExistsException("User email already exists");
        }

        storedUser.setAccount(account);
        storedUser.setName(customerDTO.getName());
        storedUser.setEmail(customerDTO.getEmail());
        storedUser.setPassword(customerDTO.getPassword());

        return Optional.of(UserMapper.convertToDTO(customerRepository.save(storedUser)));
    }

    public Optional<String> deleteUser(UUID id) {
        User storedUser = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        customerRepository.delete(storedUser);
        return Optional.of("User deleted successfully");
    }

}
