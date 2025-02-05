package com.factoria.proyecto_final_ecom_fs.service;

import com.factoria.proyecto_final_ecom_fs.dto.user.UserDTORequest;
import com.factoria.proyecto_final_ecom_fs.dto.user.UserDTOResponse;
import com.factoria.proyecto_final_ecom_fs.dto.user.UserMapper;
import com.factoria.proyecto_final_ecom_fs.exception.admin.EntityNotFoundException;
import com.factoria.proyecto_final_ecom_fs.exception.user.EmptyCartException;
import com.factoria.proyecto_final_ecom_fs.model.Category;
import com.factoria.proyecto_final_ecom_fs.model.Product;
import com.factoria.proyecto_final_ecom_fs.model.User;
import com.factoria.proyecto_final_ecom_fs.repository.ProductRepository;
import com.factoria.proyecto_final_ecom_fs.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public UserService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public User saveUser(User newUser) {
        return userRepository.save(newUser);
    }

    public List<UserDTOResponse> getUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new RuntimeException("No users found");
        }
        return users.stream().map(UserMapper::entityToDTO).toList();
    }

    public Optional<UserDTOResponse> updateUser(int id, UserDTORequest userDTORequest, Set<Product> products) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setName(userDTORequest.name());
            existingUser.setSurname(userDTORequest.surname());
            existingUser.setEmail(userDTORequest.email());
            existingUser.setPassword(userDTORequest.password());
            existingUser.setProducts(products);

            User updatedUser = userRepository.save(existingUser);

            return UserMapper.entityToDTO(updatedUser);
        });
    }

    public void deleteUser(int id) {
        if (!userRepository.existsById(id)) throw new RuntimeException("User with ID " + id + " not found");
        userRepository.deleteById(id);
    }

    public Set<User> findUsersByIds(List<Integer> usersIds) {
        return userRepository.findByIdIn(usersIds);
    }

    public Optional<User> findById(@NotNull int id) {
        return userRepository.findById(id);
    }

    public UserDTOResponse addProductsToUser (int id, Set<Integer> productsIds) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) throw new EntityNotFoundException("User  not found");

        User user = userOpt.get();

        Set<Product> productsToAdd = new HashSet<>(productRepository.findAllById(productsIds));
        user.getProducts().addAll(productsToAdd);
        User updatedUser  = userRepository.save(user);

        return UserMapper.entityToDTO(updatedUser);
    }

    public UserDTOResponse removeProductToUser (int id, Set<Integer> productsIds) {
        Optional<User> userOpt = userRepository.findById(id);

        if(userOpt.isEmpty()) throw new EntityNotFoundException("User not found");

        User user = userOpt.get();
        Set<Product> products = new HashSet<>(productRepository.findAllById(productsIds));
        user.getProducts().removeAll(products);
        User updatedEvent = userRepository.save(user);
        return UserMapper.entityToDTO(updatedEvent);
    }
}
