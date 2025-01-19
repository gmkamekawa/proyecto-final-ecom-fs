package com.factoria.proyecto_final_ecom_fs.dto.product;

public record ProductDTOResponse(
        String name,
        String description,
        float price,
        boolean feature,
        String url_image
) {
}
