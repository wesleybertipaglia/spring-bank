package com.wesleybertipaglia.bank.mappers;

import com.wesleybertipaglia.bank.dtos.UserDTO;
import com.wesleybertipaglia.bank.models.User;

public class UserMapper {

    public static UserDTO convertToDTO(User customer) {
        if (customer == null) {
            return null;
        } else if (customer.getId() == null) {
            throw new IllegalArgumentException("User ID is required");
        } else if (customer.getName() == null) {
            throw new IllegalArgumentException("User name is required");
        } else if (customer.getUsername() == null) {
            throw new IllegalArgumentException("Username is required");
        } else if (customer.getEmail() == null) {
            throw new IllegalArgumentException("User email is required");
        } else if (customer.getAccount() == null) {
            throw new IllegalArgumentException("User account is required");
        }

        return new UserDTO(customer.getId(), customer.getAccount().getId(), customer.getName(),
                customer.getUsername(), customer.getEmail());
    }

    public static User convertToEntity(UserDTO customerDTO) {
        if (customerDTO == null) {
            return null;
        } else if (customerDTO.getId() == null) {
            throw new IllegalArgumentException("User ID is required");
        } else if (customerDTO.getName() == null) {
            throw new IllegalArgumentException("User name is required");
        } else if (customerDTO.getUsername() == null) {
            throw new IllegalArgumentException("Username is required");
        } else if (customerDTO.getEmail() == null) {
            throw new IllegalArgumentException("User email is required");
        } else if (customerDTO.getPassword() == null) {
            throw new IllegalArgumentException("User password is required");
        } else if (customerDTO.getAccountId() == null) {
            throw new IllegalArgumentException("User account is required");
        }

        return new User(customerDTO.getId(), customerDTO.getName(), customerDTO.getUsername(),
                customerDTO.getEmail(), customerDTO.getPassword());
    }

}
