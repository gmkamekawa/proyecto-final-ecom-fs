package com.factoria.proyecto_final_ecom_fs.dto.user;

import com.factoria.proyecto_final_ecom_fs.model.Product;
import com.factoria.proyecto_final_ecom_fs.model.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {

    public static User dtoToEntity(UserDTORequest userDTORequest, Set<Product> products) {
        User user = new User(userDTORequest.name(), userDTORequest.surname(), userDTORequest.email(), userDTORequest.password());
        user.setProducts(products);

        return user;
    }

    public static UserDTOResponse entityToDTO(User user) {
        List<String> productsNames = user.getProducts().stream().map(Product::getName).collect(Collectors.toList());

        return new UserDTOResponse(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPassword(),
                productsNames
        );
    }
}
