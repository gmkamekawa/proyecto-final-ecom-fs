package com.factoria.proyecto_final_ecom_fs.controller;

import com.factoria.proyecto_final_ecom_fs.dto.user.UserDTORequest;
import com.factoria.proyecto_final_ecom_fs.dto.user.UserDTOResponse;
import com.factoria.proyecto_final_ecom_fs.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTOResponse> saveUser(@Valid @RequestBody UserDTORequest userDTORequest) {
        UserDTOResponse newUserResponse = userService.saveUser(userDTORequest);
        return new ResponseEntity<>(newUserResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTOResponse>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTOResponse> updateUser(
            @PathVariable int id,
            @Valid @RequestBody UserDTORequest userDTORequest) {

        return userService.updateUser(id, userDTORequest)
                .map(updatedUser -> new ResponseEntity<>(updatedUser, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
