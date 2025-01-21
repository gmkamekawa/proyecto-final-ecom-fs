package com.factoria.proyecto_final_ecom_fs.dto.category;

import jakarta.validation.constraints.NotBlank;


public record CategoryDTORequest(
        Integer id,
        @NotBlank(message = "Name is required")
        String name
)

        {

        }