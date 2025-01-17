package com.factoria.proyecto_final_ecom_fs.service;

import com.factoria.proyecto_final_ecom_fs.dto.user.UserDTORequest;
import com.factoria.proyecto_final_ecom_fs.dto.user.UserDTOResponse;
import com.factoria.proyecto_final_ecom_fs.dto.user.UserMapper;
import com.factoria.proyecto_final_ecom_fs.exception.user.EmptyCartException;
import com.factoria.proyecto_final_ecom_fs.model.User;
import com.factoria.proyecto_final_ecom_fs.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository; }

    public UserDTOResponse saveUser(UserDTORequest userDTORequest){
        User newUser = UserMapper.dtoToEntity (userDTORequest);
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

}
