package com.factoria.proyecto_final_ecom_fs.exception.user;

public class MissingUserException extends RuntimeException {
    public MissingUserException(String message) {
        super(message);
    }
}
