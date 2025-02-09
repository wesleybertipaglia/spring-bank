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
import com.wesleybertipaglia.bank.repositories.UserRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Optional<UserDTO> createUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new EntityExistsException("Username already exists");
        }

        User user = new User(userDTO.getName(), userDTO.getUsername(), userDTO.getPassword(), userDTO.getRole());
        return Optional.of(UserMapper.convertToDTO(userRepository.save(user)));
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> listUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable).map(UserMapper::convertToDTO);
    }

    @Transactional(readOnly = true)
    public Optional<UserDTO> getUserById(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }

        return Optional.of(UserMapper.convertToDTO(userRepository.findById(id).get()));
    }

    @Transactional
    public Optional<UserDTO> updateUser(UUID id, UserDTO userDTO) {
        User storedUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (userRepository.existsByUsername(userDTO.getUsername())
                && storedUser.getUsername() != userDTO.getUsername()) {
            throw new EntityExistsException("Username already exists");
        }

        storedUser.setName(userDTO.getName());
        storedUser.setUsername(userDTO.getUsername());
        storedUser.setPassword(userDTO.getPassword());
        storedUser.setRole(userDTO.getRole());

        return Optional.of(UserMapper.convertToDTO(userRepository.save(storedUser)));
    }

    public Optional<String> deleteUser(UUID id) {
        User storedUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        userRepository.delete(storedUser);
        return Optional.of("User deleted successfully");
    }

}
