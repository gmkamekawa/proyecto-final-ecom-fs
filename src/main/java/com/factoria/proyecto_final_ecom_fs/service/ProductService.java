package com.factoria.proyecto_final_ecom_fs.service;

import com.factoria.proyecto_final_ecom_fs.dto.product.ProductDTORequest;
import com.factoria.proyecto_final_ecom_fs.dto.product.ProductDTOResponse;
import com.factoria.proyecto_final_ecom_fs.dto.product.ProductMapper;
import com.factoria.proyecto_final_ecom_fs.dto.user.UserDTOResponse;
import com.factoria.proyecto_final_ecom_fs.dto.user.UserMapper;
import com.factoria.proyecto_final_ecom_fs.exception.admin.EntityNotFoundException;
import com.factoria.proyecto_final_ecom_fs.model.Category;
import com.factoria.proyecto_final_ecom_fs.model.Product;
import com.factoria.proyecto_final_ecom_fs.model.User;
import com.factoria.proyecto_final_ecom_fs.repository.CategoryRepository;
import com.factoria.proyecto_final_ecom_fs.repository.ProductRepository;
import com.factoria.proyecto_final_ecom_fs.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public Product saveProduct(Product newProduct) {
        int categoryId = newProduct.getCategory().getId();

        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            newProduct.setCategory(category);

            return productRepository.save(newProduct);
        }

        throw new RuntimeException("Category not found");
    }

    public List<ProductDTOResponse> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductMapper::entityToDTO).toList();
    }

    public Optional<ProductDTOResponse> updateProduct(int id, ProductDTORequest productDTORequest, Category category, Set<User> users) {
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setName(productDTORequest.name());
            existingProduct.setPrice(productDTORequest.price());
            existingProduct.setUrl_image(productDTORequest.url_image());
            existingProduct.setFeatured(productDTORequest.featured());
            existingProduct.setDescription(productDTORequest.description());
            existingProduct.setCategory(category);
            existingProduct.setUsers(users);

            Product updatedProduct = productRepository.save(existingProduct);

            return ProductMapper.entityToDTO(updatedProduct);
        });
    }

    public void deleteProduct(int id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product with ID " + id + " not found");
        }

        productRepository.deleteById(id);
    }

    public Set<Product> findProductsByIds(List<Integer> productsIds) {
        return productRepository.findByIdIn(productsIds);
    }

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    public void addProductsToUser(int id, Set<Integer> userIds) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        List<Product> products = productRepository.findAllById(userIds);
        user.getProducts().addAll(products);
        userRepository.save(user);
    }

    public void removeProductsToUser(int id, Set<Integer> userIds) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        List<Product> products = productRepository.findAllById(userIds);
        products.forEach(user.getProducts()::remove);
        userRepository.save(user);
    }
}
