package com.factoria.proyecto_final_ecom_fs.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public record ProductDTORequest(
        String name,
        float price,
        String url_image,  // Esperamos una URL
        boolean featured,
        String description
) {
}
