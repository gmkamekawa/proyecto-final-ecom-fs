package com.factoria.proyecto_final_ecom_fs.service;

import com.factoria.proyecto_final_ecom_fs.dto.product.ProductDTORequest;
import com.factoria.proyecto_final_ecom_fs.dto.product.ProductDTOResponse;
import com.factoria.proyecto_final_ecom_fs.dto.product.ProductMapper;
import com.factoria.proyecto_final_ecom_fs.model.Category;
import com.factoria.proyecto_final_ecom_fs.model.Product;
import com.factoria.proyecto_final_ecom_fs.model.User;
import com.factoria.proyecto_final_ecom_fs.repository.CategoryRepository;
import com.factoria.proyecto_final_ecom_fs.repository.ProductRepository;
import com.factoria.proyecto_final_ecom_fs.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return products.stream().map(product -> ProductMapper.entityToDTO(product)).toList();
    }

    public Optional<ProductDTOResponse> updateProduct(int id, ProductDTORequest productDTORequest, Category category, List<User> users) {
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

    public List<User> findProductByIds(List<Integer> userIds) {
        return userRepository.findByIdIn(userIds);
    }

    public Optional<Product> findById(int productId) {
        return productRepository.findById(productId);
    }
}
