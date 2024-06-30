package com.wesleybertipaglia.bank.services;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wesleybertipaglia.bank.dtos.CustomerDTO;
import com.wesleybertipaglia.bank.mappers.CustomerMapper;
import com.wesleybertipaglia.bank.models.Customer;
import com.wesleybertipaglia.bank.models.Account;
import com.wesleybertipaglia.bank.repositories.CustomerRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import com.wesleybertipaglia.bank.repositories.AccountRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public Optional<CustomerDTO> createCustomer(CustomerDTO customerDTO) {
        if (customerRepository.existsByEmail(customerDTO.getEmail())) {
            throw new EntityExistsException("Customer email already exists");
        }

        Account account = accountRepository.findById(customerDTO.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        if (customerRepository.existsByAccount(account)) {
            throw new EntityExistsException("Account already has a customer");
        }

        Customer customer = new Customer(customerDTO.getName(), customerDTO.getEmail(), customerDTO.getPassword(),
                account);
        return Optional.of(CustomerMapper.convertToDTO(customerRepository.save(customer)));
    }

    @Transactional(readOnly = true)
    public Page<CustomerDTO> listCustomers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepository.findAll(pageable).map(CustomerMapper::convertToDTO);
    }

    @Transactional(readOnly = true)
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("Customer not found");
        }

        return Optional.of(CustomerMapper.convertToDTO(customerRepository.findById(id).get()));
    }

    @Transactional
    public Optional<CustomerDTO> updateCustomer(UUID id, CustomerDTO customerDTO) {
        Customer storedCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        Account account = accountRepository.findById(customerDTO.getAccountId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        if (customerRepository.existsByAccount(account)
                && storedCustomer.getAccount().getId() != customerDTO.getAccountId()) {
            throw new EntityExistsException("Account already has a customer");
        }

        if (customerRepository.existsByEmail(customerDTO.getEmail())
                && storedCustomer.getEmail() != customerDTO.getEmail()) {
            throw new EntityExistsException("Custumer email already exists");
        }

        storedCustomer.setAccount(account);
        storedCustomer.setName(customerDTO.getName());
        storedCustomer.setEmail(customerDTO.getEmail());
        storedCustomer.setPassword(customerDTO.getPassword());

        return Optional.of(CustomerMapper.convertToDTO(customerRepository.save(storedCustomer)));
    }

    public Optional<String> deleteCustomer(UUID id) {
        Customer storedCustumer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        customerRepository.delete(storedCustumer);
        return Optional.of("Customer deleted successfully");
    }

}
