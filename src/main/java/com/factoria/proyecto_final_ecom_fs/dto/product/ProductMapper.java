package com.factoria.proyecto_final_ecom_fs.dto.product;

import com.factoria.proyecto_final_ecom_fs.model.Category;
import com.factoria.proyecto_final_ecom_fs.model.Product;
import com.factoria.proyecto_final_ecom_fs.model.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductMapper {
    public static Product dtoToEntity(ProductDTORequest productDTORequest, Category category, Set<User> users) {
        Product product = new Product(productDTORequest.name(), productDTORequest.price(), productDTORequest.url_image(), productDTORequest.featured(), productDTORequest.description(), category);
        product.setUsers(users);

        return product;
    }

    public static ProductDTOResponse entityToDTO(Product product) {
        List<String> userNames = product.getUsers().stream()
                .map(User::getName)
                .collect(Collectors.toList());
        String categoryName = (product.getCategory() != null)? product.getCategory().getName() : "No category";

        return new ProductDTOResponse(product.getId(),
                product.getName(),
                product.getPrice(),
                product.getUrl_image(),
                product.isFeatured(),
                product.getDescription(),
                categoryName,
                userNames);
    }
}
