package com.factoria.proyecto_final_ecom_fs.controller;

import com.factoria.proyecto_final_ecom_fs.dto.product.ProductDTORequest;
import com.factoria.proyecto_final_ecom_fs.dto.product.ProductDTOResponse;
import com.factoria.proyecto_final_ecom_fs.model.Product;
import com.factoria.proyecto_final_ecom_fs.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //CRUD
    //Create
    @PostMapping
    public ResponseEntity<ProductDTOResponse> saveProduct(@Valid @RequestBody ProductDTORequest pRequest) {
        return new  ResponseEntity<>(productService.saveProduct(pRequest), HttpStatus.CREATED);
    }

    //Read
    @GetMapping
    public ResponseEntity<List<ProductDTOResponse>> getProducts() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    //Update
    @PutMapping("/id={id}")
    public ResponseEntity<ProductDTOResponse> updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        try {
            ProductDTOResponse product = productService.editProduct(id, updatedProduct);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Delete
    @DeleteMapping("/id={id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }
}
