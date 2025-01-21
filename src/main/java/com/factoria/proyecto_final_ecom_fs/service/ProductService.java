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

    //CRUD
    //Create
    public ProductDTOResponse saveProduct(ProductDTORequest pRequest) {
        Product newProduct = ProductMapper.dtoToEntity(pRequest);
        Product savedProduct = productRepository.save(newProduct);
        return ProductMapper.entityToDTO(savedProduct);
    }

    //Read
    public List<ProductDTOResponse> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> ProductMapper.entityToDTO(product)).toList();
    }

    //Edit
    public ProductDTOResponse editProduct(int id, Product updatedProduct) {
        Optional<Product> foundProduct = productRepository.findById(id);

        if (foundProduct.isPresent()) {
            Product existingProduct = foundProduct.get();

            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setUrl_image(updatedProduct.getUrl_image());
            existingProduct.setFeature(updatedProduct.isFeature());
            existingProduct.setDescription(updatedProduct.getDescription());

            Product savedProduct = productRepository.save(existingProduct);

            return ProductMapper.entityToDTO(savedProduct);
        }

        throw new RuntimeException("Product not found with id: " + id);
    }

    //Delete
    public void deleteProduct(int id) {
        if (!productRepository.existsById(id)) throw new RuntimeException("Product with ID " + id + " not found");

        productRepository.deleteById(id);
    }
}
