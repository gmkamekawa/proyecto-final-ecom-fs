package com.factoria.proyecto_final_ecom_fs.exception;

public class EmptyException extends RuntimeException {
    public EmptyException() {
        super("No data");
    }
}
