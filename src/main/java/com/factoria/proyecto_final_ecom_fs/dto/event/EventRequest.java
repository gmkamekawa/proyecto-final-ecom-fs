package com.factoria.proyecto_final_ecom_fs.dto.event;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

public record EventRequest(
        @NotBlank(message = "Title is required")
        String title,
        @NotBlank(message = "description is required")
        String description,
        @URL(
                protocol = "https",
                host = "example.com",
                message = "The image url is required"
        )
        String image_url,
        @NotBlank(message = "Location is required")
        String location,
        @Future(message = "Date is required")
        LocalDateTime date,
        Boolean is_available
) {
}
