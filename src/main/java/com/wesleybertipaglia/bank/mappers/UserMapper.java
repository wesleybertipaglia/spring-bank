package com.wesleybertipaglia.bank.mappers;

import com.wesleybertipaglia.bank.dtos.UserDTO;
import com.wesleybertipaglia.bank.models.User;

public class UserMapper {

    public static UserDTO convertToDTO(User user) {
        if (user == null) {
            return null;
        } else if (user.getId() == null) {
            throw new IllegalArgumentException("User ID is required");
        } else if (user.getName() == null) {
            throw new IllegalArgumentException("User name is required");
        } else if (user.getUsername() == null) {
            throw new IllegalArgumentException("Username is required");
        } else if (user.getEmail() == null) {
            throw new IllegalArgumentException("User email is required");
        } else if (user.getRole() == null) {
            throw new IllegalArgumentException("User role is required");
        } else if (user.getAccount() == null) {
            throw new IllegalArgumentException("User account is required");
        }

        return new UserDTO(user.getId(), user.getAccount().getId(), user.getName(),
                user.getUsername(), user.getEmail(), user.getRole());
    }

    public static User convertToEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        } else if (userDTO.getId() == null) {
            throw new IllegalArgumentException("User ID is required");
        } else if (userDTO.getName() == null) {
            throw new IllegalArgumentException("User name is required");
        } else if (userDTO.getUsername() == null) {
            throw new IllegalArgumentException("Username is required");
        } else if (userDTO.getEmail() == null) {
            throw new IllegalArgumentException("User email is required");
        } else if (userDTO.getPassword() == null) {
            throw new IllegalArgumentException("User password is required");
        } else if (userDTO.getRole() == null) {
            throw new IllegalArgumentException("User role is required");
        } else if (userDTO.getAccountId() == null) {
            throw new IllegalArgumentException("User account is required");
        }

        return new User(userDTO.getId(), userDTO.getName(), userDTO.getUsername(),
                userDTO.getEmail(), userDTO.getPassword(), userDTO.getRole());
    }

}
