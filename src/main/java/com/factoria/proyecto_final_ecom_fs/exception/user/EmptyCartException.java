package com.factoria.proyecto_final_ecom_fs.exception.user;

public class EmptyCartException extends RuntimeException {
    public EmptyCartException(String message) {
        super(message);
    }
}
