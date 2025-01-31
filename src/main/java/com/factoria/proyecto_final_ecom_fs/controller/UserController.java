package com.factoria.proyecto_final_ecom_fs.controller;

import com.factoria.proyecto_final_ecom_fs.dto.product.ProductMapper;
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
        User newUser = UserMapper.dtoToEntity(userDTORequest);
        UserDTOResponse newUserResponse = userService.saveUser(newUser);

        return new ResponseEntity<>(newUserResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTOResponse>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTOResponse> updateUser(@PathVariable int id, @Valid @RequestBody UserDTORequest userDTORequest) {
        return userService.updateUser(id, userDTORequest)
                .map(updatedUser -> new ResponseEntity<>(updatedUser, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<Product>> getUserProducts(@PathVariable int id) {
        Optional<User> user = userService.findById(id);

        return user.map(value -> ResponseEntity.ok(value.getProducts())).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{userId}/products/{productId}")
    public ResponseEntity<UserDTOResponse> removeProductFromUser (@PathVariable int userId, @PathVariable int productId) {
        try {
            Optional<User> user = userService.findById(userId);
            Optional<Product> product = productService.findById(productId);

            if (user.isEmpty()) throw new ObjectNotFoundException("User", userId);
            if (product.isEmpty()) throw new ObjectNotFoundException("Product", productId);

            User existingUser = user.get();
            Product existingProduct = product.get();

            existingUser.getProducts().remove(product.get());
            existingProduct.getUsers().remove(user.get());

            UserDTOResponse userResponse = userService.saveUser(existingUser);
            productService.saveProduct(existingProduct);

            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException("Invalid id data: " + e.getMessage());
        }
    }
}
