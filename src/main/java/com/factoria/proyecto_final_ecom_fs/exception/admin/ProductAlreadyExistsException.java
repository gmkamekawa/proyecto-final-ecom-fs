package com.factoria.proyecto_final_ecom_fs.exception.admin;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
