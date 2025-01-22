package com.factoria.proyecto_final_ecom_fs.dto.product;

import com.factoria.proyecto_final_ecom_fs.model.Product;

public class ProductMapper {
    public static Product dtoToEntity(ProductDTORequest pRequest) {
        return new Product(pRequest.name(), pRequest.price(), pRequest.url_image(), pRequest.feature(), pRequest.description());
    }

    public static ProductDTOResponse entityToDTO(Product product) {
        return new ProductDTOResponse(product.getId(),product.getName(), product.getPrice(), product.getUrl_image(), product.isFeature(), product.getDescription());
    }
}
