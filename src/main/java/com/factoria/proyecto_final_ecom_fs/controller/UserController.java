package com.factoria.proyecto_final_ecom_fs.controller;

import com.factoria.proyecto_final_ecom_fs.dto.user.UserDTORequest;
import com.factoria.proyecto_final_ecom_fs.dto.user.UserDTOResponse;
import com.factoria.proyecto_final_ecom_fs.dto.user.UserMapper;
import com.factoria.proyecto_final_ecom_fs.model.Product;
import com.factoria.proyecto_final_ecom_fs.model.User;
import com.factoria.proyecto_final_ecom_fs.service.ProductService;
import com.factoria.proyecto_final_ecom_fs.service.UserService;
import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final ProductService productService;

    public UserController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<UserDTOResponse> saveUser(@Valid @RequestBody UserDTORequest userDTORequest) {
        if (userDTORequest.products_Id() == null || userDTORequest.products_Id().isEmpty()) throw new ObjectNotFoundException("User", userDTORequest.products_Id());

        Set<Product> products = productService.findProductsByIds(userDTORequest.products_Id());

        User newUser = UserMapper.dtoToEntity(userDTORequest, products);
        User createdUser = userService.saveUser(newUser);
        UserDTOResponse newUserDTO = UserMapper.entityToDTO(createdUser);

        return new ResponseEntity<>(newUserDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTOResponse>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTOResponse> updateUser(@PathVariable int id, @Valid @RequestBody UserDTORequest userDTORequest) {
        try {
            if (userDTORequest.products_Id() == null || userDTORequest.products_Id().isEmpty()) throw new ObjectNotFoundException("Products", userDTORequest.products_Id());

            Set<Product> products = productService.findProductsByIds(userDTORequest.products_Id());

            return userService.updateUser(id, userDTORequest, products)
                    .map(updatedUser -> new ResponseEntity<>(updatedUser, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            throw new RuntimeException("Invalid product data: " + e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<Set<Product>> getUserProducts(@PathVariable int id) {
        Optional<User> user = userService.findById(id);

        return user.map(value -> ResponseEntity.ok(value.getProducts())).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/{id}/product")
    public ResponseEntity<UserDTOResponse> addProductToUser(@PathVariable int id, @RequestBody Set<Integer> productsIds) {
        UserDTOResponse userDTOResponse = userService.addProductsToUser(id, productsIds);
        return new ResponseEntity<>(userDTOResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/product")
    public ResponseEntity<UserDTOResponse> removeProductToUser(@PathVariable int id, @RequestBody Set<Integer> productsIds) {
        UserDTOResponse userDTOResponse = userService.removeProductToUser(id, productsIds);
        return new ResponseEntity<>(userDTOResponse, HttpStatus.OK);
    }
}
