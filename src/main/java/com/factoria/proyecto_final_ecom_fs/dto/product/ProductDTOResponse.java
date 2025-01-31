package com.factoria.proyecto_final_ecom_fs.dto.product;

import java.util.List;

public record ProductDTOResponse(
        int id,
        String name,
        float price,
        String url_image,
        boolean featured,
        String description,
        String category,
        List<String> users
) {
}
