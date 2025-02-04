package com.factoria.proyecto_final_ecom_fs.repository;

import com.factoria.proyecto_final_ecom_fs.model.Category;
import com.factoria.proyecto_final_ecom_fs.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Set<Product> findByIdIn(List<Integer> ids);
    List<Product> findByCategory(Category category);
    List<Product> findByPriceBetween(float minPrice, float maxPrice);
}
