package com.factoria.proyecto_final_ecom_fs.dto.category;

import com.factoria.proyecto_final_ecom_fs.model.Product;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CategoryDTORequest (
        Integer id,
        @NotBlank(message = "Name is required")
        String name,
        List<Product> products
) {
}