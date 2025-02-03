package com.factoria.proyecto_final_ecom_fs.repository;

import com.factoria.proyecto_final_ecom_fs.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findById(Integer id); // Este método ya existe por defecto, pero puedes personalizarlo si es necesario
}
