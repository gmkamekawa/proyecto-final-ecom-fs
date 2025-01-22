package com.factoria.proyecto_final_ecom_fs.service;


import com.factoria.proyecto_final_ecom_fs.dto.product.ProductDTORequest;
import com.factoria.proyecto_final_ecom_fs.dto.product.ProductDTOResponse;
import com.factoria.proyecto_final_ecom_fs.dto.product.ProductMapper;
import com.factoria.proyecto_final_ecom_fs.model.Product;
import com.factoria.proyecto_final_ecom_fs.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public ProductDTOResponse saveProduct(ProductDTORequest productDTORequest) {
        Product newProduct = ProductMapper.dtoToEntity(productDTORequest);
        Product savedProduct = productRepository.save(newProduct);
        return ProductMapper.entityToDTO(savedProduct);
    }

    public List<ProductDTOResponse> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> ProductMapper.entityToDTO(product)).toList();
    }

    public Optional<ProductDTOResponse> updateProduct(int id, ProductDTORequest productDTORequest) {
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setName(productDTORequest.name());
            existingProduct.setFeatured(productDTORequest.featured());
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
}
