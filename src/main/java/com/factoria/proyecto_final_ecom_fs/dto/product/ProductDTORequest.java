package com.factoria.proyecto_final_ecom_fs.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public record ProductDTORequest(
        @NotBlank(message = "Name is required")
        String name,
        @NotNull(message = "Price is required")
        float price,
//        @URL(
//                protocol = "https",
//                host = "xxxxxxxxx",
//                message = "The image URL is not correct"
//        )
        String url_image,
        @NotNull(message = "This field must be true or false")
        boolean feature,
        @NotBlank(message = "Description is required")
        String description
) {
}
