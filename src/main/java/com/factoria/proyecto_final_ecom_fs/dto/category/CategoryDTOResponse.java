package com.factoria.proyecto_final_ecom_fs.dto.category;

import com.factoria.proyecto_final_ecom_fs.model.Product;

import java.util.List;

public record CategoryDTOResponse (
        int id,
        String name,
        List<Product> products
) {
}
