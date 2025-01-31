package com.factoria.proyecto_final_ecom_fs.controller;

import com.factoria.proyecto_final_ecom_fs.dto.product.ProductDTORequest;
import com.factoria.proyecto_final_ecom_fs.dto.product.ProductDTOResponse;
import com.factoria.proyecto_final_ecom_fs.dto.product.ProductMapper;
import com.factoria.proyecto_final_ecom_fs.model.Category;
import com.factoria.proyecto_final_ecom_fs.model.Product;
import com.factoria.proyecto_final_ecom_fs.model.User;
import com.factoria.proyecto_final_ecom_fs.service.CategoryService;
import com.factoria.proyecto_final_ecom_fs.service.ProductService;
import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<ProductDTOResponse> saveProduct(@Valid @RequestBody ProductDTORequest productDTORequest) {
        try {
            Optional<Category> optionalCategory = categoryService.findCategory(productDTORequest.category_Id());

            if (optionalCategory.isEmpty()) throw new ObjectNotFoundException("Category", productDTORequest.category_Id());
            if (productDTORequest.users_Id() == null || productDTORequest.users_Id().isEmpty()) throw new ObjectNotFoundException("User", productDTORequest.users_Id());

            List<User> users = productService.findProductByIds(productDTORequest.users_Id());

            Product newProduct = ProductMapper.dtoToEntity(productDTORequest, optionalCategory.get(), users);
            Product createdProduct = productService.saveProduct(newProduct);
            ProductDTOResponse newProductDTO = ProductMapper.entityToDTO(createdProduct);

            return new ResponseEntity<>(newProductDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException("Invalid product data: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductDTOResponse>> getProducts() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTOResponse> updateProduct(@PathVariable int id, @Valid @RequestBody ProductDTORequest productDTORequest) {
        try {
            Optional<Category> optionalCategory = categoryService.findCategory(productDTORequest.category_Id());

            if (optionalCategory.isEmpty()) throw new ObjectNotFoundException("Category", productDTORequest.category_Id());
            if (productDTORequest.users_Id() == null || productDTORequest.users_Id().isEmpty()) throw new ObjectNotFoundException("User", productDTORequest.users_Id());

            List<User> users = productService.findProductByIds(productDTORequest.users_Id());

            return productService.updateProduct(id, productDTORequest, optionalCategory.get(), users)
                    .map(updatedProduct -> new ResponseEntity<>(updatedProduct, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            throw new RuntimeException("Invalid product data: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
