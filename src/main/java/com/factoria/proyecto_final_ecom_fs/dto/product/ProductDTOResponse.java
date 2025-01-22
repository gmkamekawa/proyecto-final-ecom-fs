package com.factoria.proyecto_final_ecom_fs.dto.product;

public record ProductDTOResponse(
        int id,
        String name,
        float price,
        String url_image,
        boolean featured,
        String description
) {
}
