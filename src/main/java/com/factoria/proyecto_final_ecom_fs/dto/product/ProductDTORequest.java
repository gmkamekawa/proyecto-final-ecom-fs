package com.factoria.proyecto_final_ecom_fs.dto.product;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record ProductDTORequest(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Description is required")
        String description,
        @NotBlank(message = "Price is required")
        float price,
        @NotBlank(message = "This field must be true or false")
        boolean feature,
        @URL(
                protocol = "https",
                host = "example.com",
                message = "The image URL is not correct"
        )
        String url_image
) {
}
