package com.factoria.proyecto_final_ecom_fs.service;

import com.factoria.proyecto_final_ecom_fs.dto.category.CategoryDTORequest;
import com.factoria.proyecto_final_ecom_fs.dto.category.CategoryDTOResponse;
import com.factoria.proyecto_final_ecom_fs.dto.category.CategoryMapper;
import com.factoria.proyecto_final_ecom_fs.model.Category;
import com.factoria.proyecto_final_ecom_fs.repository.CategoryRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTOResponse saveCategory(CategoryDTORequest categoryDTORequest) {
        Category newCategory = CategoryMapper.dtoToEntity(categoryDTORequest);
        Category savedCategory = categoryRepository.save(newCategory);

        return CategoryMapper.entityToDTO(savedCategory);
    }

    public List<CategoryDTOResponse> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new RuntimeException("No categories found");
        }

        return categories.stream()
                .map(CategoryMapper::entityToDTO)
                .toList();
    }

    public Optional<CategoryDTOResponse> updateCategory(int id, CategoryDTORequest categoryDTORequest) {
        return categoryRepository.findById(id).map(existingCategory -> {
            existingCategory.setName(categoryDTORequest.name());
            Category updatedCategory = categoryRepository.save(existingCategory);
            return CategoryMapper.entityToDTO(updatedCategory);
        });
    }

    public void deleteCategory(int id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category with ID " + id + " not found");
        }

        categoryRepository.deleteById(id);
    }

    // Método para buscar una categoría por ID
    public Optional<Category> findCategory(@NotNull int categoryId) {
        return categoryRepository.findById(categoryId);  // Ya está correctamente implementado
    }
}
