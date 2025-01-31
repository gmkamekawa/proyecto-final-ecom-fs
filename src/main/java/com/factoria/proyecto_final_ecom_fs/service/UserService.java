package com.factoria.proyecto_final_ecom_fs.service;

import com.factoria.proyecto_final_ecom_fs.dto.user.UserDTORequest;
import com.factoria.proyecto_final_ecom_fs.dto.user.UserDTOResponse;
import com.factoria.proyecto_final_ecom_fs.dto.user.UserMapper;
import com.factoria.proyecto_final_ecom_fs.exception.user.EmptyCartException;
import com.factoria.proyecto_final_ecom_fs.model.Category;
import com.factoria.proyecto_final_ecom_fs.model.User;
import com.factoria.proyecto_final_ecom_fs.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTOResponse saveUser(User newUser){
        User savedUser = userRepository.save(newUser);
        return UserMapper.entityToDTO(savedUser);
    }

    public List<UserDTOResponse> getUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new RuntimeException("No users found");
        }
        return users.stream().map(user -> UserMapper.entityToDTO(user)).toList();
    }

    public Optional<UserDTOResponse> updateUser(int id, UserDTORequest userDTORequest) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setName(userDTORequest.name());
            existingUser.setSurname(userDTORequest.surname());
            existingUser.setEmail(userDTORequest.email());
            existingUser.setPassword(userDTORequest.password());
            User updatedUser = userRepository.save(existingUser);

            return UserMapper.entityToDTO(updatedUser);
        });
    }

    public void deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User with ID " + id + " not found");
        }
        userRepository.deleteById(id);
    }

    public Optional<User> findById(@NotNull int id) {
        return userRepository.findById(id);
    }
}

