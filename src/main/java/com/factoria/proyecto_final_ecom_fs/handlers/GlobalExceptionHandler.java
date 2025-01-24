package com.factoria.proyecto_final_ecom_fs.handlers;

import com.factoria.proyecto_final_ecom_fs.dto.ErrorResponseDTO;
import com.factoria.proyecto_final_ecom_fs.exception.admin.EntityNotFoundException;
import com.factoria.proyecto_final_ecom_fs.exception.admin.InvalidPriceException;
import com.factoria.proyecto_final_ecom_fs.exception.admin.MissingFieldException;
import com.factoria.proyecto_final_ecom_fs.exception.admin.ProductAlreadyExistsException;
import com.factoria.proyecto_final_ecom_fs.exception.user.EmptyCartException;
import com.factoria.proyecto_final_ecom_fs.exception.user.MissingUserException;
import com.factoria.proyecto_final_ecom_fs.exception.user.SingleCategorySelectionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> EntityNotFoundException(EntityNotFoundException ex) {
        String message = "No es posible encontrar la entidad";
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(message, HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPriceException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidPriceException(InvalidPriceException ex) {
        String message = "Precio no válido. Debe ser superior a 0.";
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(message, HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MissingFieldException.class)
    public ResponseEntity<ErrorResponseDTO> handleMissingFieldException(MissingFieldException ex) {
        String message = "Todos los campos son obligatorios.";
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(message, HttpStatus.PARTIAL_CONTENT.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.PARTIAL_CONTENT);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductAlreadyExistsException(Exception ex) {
        String message = "Este producto ya existe";
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(message, HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmptyCartException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmptyCartException(Exception ex) {
        String message = "El carrito de compra está vacío.";
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(message, HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(MissingUserException.class)
    public ResponseEntity<ErrorResponseDTO> handleMissingUserException(Exception ex) {
        String message = "Seleccione usuario para continuar.";
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(message, HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(SingleCategorySelectionException.class)
    public ResponseEntity<ErrorResponseDTO> handleSingleCategorySelectionException(Exception ex) {
        String message = "Ya hay una categoría seleccionada.";
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(message, HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

}
