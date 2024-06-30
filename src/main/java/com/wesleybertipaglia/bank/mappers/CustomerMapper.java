package com.wesleybertipaglia.bank.mappers;

import com.wesleybertipaglia.bank.dtos.CustomerDTO;
import com.wesleybertipaglia.bank.models.Customer;

public class CustomerMapper {

    public static CustomerDTO convertToDTO(Customer customer) {
        if (customer == null) {
            return null;
        } else if (customer.getId() == null) {
            throw new IllegalArgumentException("Customer ID is required");
        } else if (customer.getName() == null) {
            throw new IllegalArgumentException("Customer name is required");
        } else if (customer.getEmail() == null) {
            throw new IllegalArgumentException("Customer email is required");
        } else if (customer.getAccount() == null) {
            throw new IllegalArgumentException("Customer account is required");
        }

        return new CustomerDTO(customer.getId(), customer.getAccount().getId(), customer.getName(),
                customer.getEmail());
    }

    public static Customer convertToEntity(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return null;
        } else if (customerDTO.getId() == null) {
            throw new IllegalArgumentException("Customer ID is required");
        } else if (customerDTO.getName() == null) {
            throw new IllegalArgumentException("Customer name is required");
        } else if (customerDTO.getEmail() == null) {
            throw new IllegalArgumentException("Customer email is required");
        } else if (customerDTO.getPassword() == null) {
            throw new IllegalArgumentException("Customer password is required");
        } else if (customerDTO.getAccountId() == null) {
            throw new IllegalArgumentException("Customer account is required");
        }

        return new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getEmail(),
                customerDTO.getPassword());
    }

}
