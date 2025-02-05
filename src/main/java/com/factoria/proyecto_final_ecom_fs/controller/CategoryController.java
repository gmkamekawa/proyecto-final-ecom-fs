package com.factoria.proyecto_final_ecom_fs.controller;

import com.factoria.proyecto_final_ecom_fs.dto.category.CategoryDTORequest;
import com.factoria.proyecto_final_ecom_fs.dto.category.CategoryDTOResponse;
import com.factoria.proyecto_final_ecom_fs.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/category")
    public class CategoryController {

        private final CategoryService categoryService;

        public CategoryController(CategoryService categoryService) {
            this.categoryService = categoryService;
        }

        @PostMapping
        public ResponseEntity<CategoryDTOResponse> saveCategory(@Valid @RequestBody CategoryDTORequest categoryDTORequest) {
            CategoryDTOResponse newCategoryResponse = categoryService.saveCategory(categoryDTORequest);
            return new ResponseEntity<>(newCategoryResponse, HttpStatus.CREATED);
        }

        @GetMapping
        public ResponseEntity<List<CategoryDTOResponse>> getCategories() {
            return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
        }

        @PutMapping("/{id}")
        public ResponseEntity<CategoryDTOResponse> updateCategory(
                @PathVariable int id,
                @Valid @RequestBody CategoryDTORequest categoryDTORequest) {

            return categoryService.updateCategory(id, categoryDTORequest)
                    .map(updatedCategory -> new ResponseEntity<>(updatedCategory, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


