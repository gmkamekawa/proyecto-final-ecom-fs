package com.factoria.proyecto_final_ecom_fs.dto.product;

public record ProductDTOResponse(
        String name,
        float price,
        String url_image,
        boolean feature,
        String description
) {
}
