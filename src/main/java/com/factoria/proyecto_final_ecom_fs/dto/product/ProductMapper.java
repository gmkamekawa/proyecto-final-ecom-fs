package com.factoria.proyecto_final_ecom_fs.dto.product;

import com.factoria.proyecto_final_ecom_fs.model.Product;

public class ProductMapper {
    public static Product dtoToEntity(ProductDTORequest productDTORequest) {
        return new Product(productDTORequest.name(), productDTORequest.price(), productDTORequest.url_image(), productDTORequest.featured(), productDTORequest.description());
    }

    public static ProductDTOResponse entityToDTO(Product product) {
        return new ProductDTOResponse(product.getId(),product.getName(), product.getPrice(), product.getUrl_image(), product.isFeatured(), product.getDescription());
    }
}
