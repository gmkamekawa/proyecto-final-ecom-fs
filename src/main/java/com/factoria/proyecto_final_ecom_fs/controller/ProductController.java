package com.factoria.proyecto_final_ecom_fs.controller;

import com.factoria.proyecto_final_ecom_fs.dto.product.ProductDTORequest;
import com.factoria.proyecto_final_ecom_fs.dto.product.ProductDTOResponse;
import com.factoria.proyecto_final_ecom_fs.dto.product.ProductMapper;
import com.factoria.proyecto_final_ecom_fs.model.Category;
import com.factoria.proyecto_final_ecom_fs.model.Product;
import com.factoria.proyecto_final_ecom_fs.model.User;
import com.factoria.proyecto_final_ecom_fs.service.CategoryService;
import com.factoria.proyecto_final_ecom_fs.service.ProductService;
import com.factoria.proyecto_final_ecom_fs.service.UserService;
import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;

    public ProductController(ProductService productService, CategoryService categoryService, UserService userService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ProductDTOResponse> saveProduct(@Valid @RequestBody ProductDTORequest productDTORequest) {
        try {
            Optional<Category> optionalCategory = categoryService.findCategory(productDTORequest.category_Id());

            if (optionalCategory.isEmpty())
                throw new ObjectNotFoundException("Category", productDTORequest.category_Id());

            Set<User> users = (productDTORequest.users_Id() != null && !productDTORequest.users_Id().isEmpty())
                    ? userService.findUsersByIds(productDTORequest.users_Id())
                    : Collections.emptySet();

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
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTOResponse> getProductById(@PathVariable int id) {
        Optional<ProductDTOResponse> product = productService.getProductById(id);

        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTOResponse> updateProduct(@PathVariable int id, @Valid @RequestBody ProductDTORequest productDTORequest) {
        try {
            Optional<Category> optionalCategory = categoryService.findCategory(productDTORequest.category_Id());

            if (optionalCategory.isEmpty())
                throw new ObjectNotFoundException("Category", productDTORequest.category_Id());
            Set<User> users = (productDTORequest.users_Id() != null && !productDTORequest.users_Id().isEmpty())
                    ? userService.findUsersByIds(productDTORequest.users_Id())
                    : Collections.emptySet();


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

    @PostMapping("/user/{id}")
    public ResponseEntity<?> addProductToUser(@PathVariable int id, @RequestBody Map<String, Set<Integer>> request) {
        Set<Integer> userIds = request.get("products");
        productService.addProductsToUser(id, userIds);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> removeProductsToUser(@PathVariable int id, @RequestBody Map<String, Set<Integer>> request) {
        Set<Integer> userIds = request.get("products");
        productService.removeProductsToUser(id, userIds);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable int categoryId) {
        Optional<Category> optionalCategory = categoryService.findCategory(categoryId);

        if (optionalCategory.isPresent()) {
            List<Product> products = productService.getProductsByCategory(optionalCategory.get());
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/price/range") //product/price/range?minPrice=10.0&maxPrice=50.0
    public ResponseEntity<List<Product>> getProductsByPriceRange(@RequestParam float minPrice, @RequestParam float maxPrice) {
        List<Product> products = productService.getProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }
}
