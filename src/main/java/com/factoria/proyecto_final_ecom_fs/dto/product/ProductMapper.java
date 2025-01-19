package com.factoria.proyecto_final_ecom_fs.dto.product;

import com.factoria.proyecto_final_ecom_fs.model.Product;

public class ProductMapper {
    public static Product dtoToEntity(ProductDTORequest pRequest) {
        return new Product(pRequest.name(), pRequest.description(), pRequest.price(), pRequest.feature(), pRequest.url_image());
    }

    public static ProductDTOResponse entityToDTO(Product product) {
        return new ProductDTOResponse(product.getName(), product.getDescription(), product.getPrice(), product.isFeature(), product.getUrl_image());
    }
}
