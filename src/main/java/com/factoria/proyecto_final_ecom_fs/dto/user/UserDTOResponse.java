package com.factoria.proyecto_final_ecom_fs.dto.user;

import com.factoria.proyecto_final_ecom_fs.model.Product;

import java.util.List;

public record UserDTOResponse(
        int id,
        String name,
        String surname,
        String email,
        String password,
        List<Product> products
) {
}
