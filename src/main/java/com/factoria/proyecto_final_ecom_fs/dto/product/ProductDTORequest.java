package com.factoria.proyecto_final_ecom_fs.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public record ProductDTORequest(
        @NotBlank
        String name,
        @NotNull
        float price,
        @URL
        String url_image,
        @NotNull
        boolean featured,
        @NotBlank
        String description

) {
}
