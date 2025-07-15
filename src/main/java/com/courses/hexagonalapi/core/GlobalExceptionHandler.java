package com.courses.hexagonalapi.core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorDTO>> handleException(BusinessException ex) {
        var errorDto = new ErrorDTO(
                ex.getErrorCode().toString(),
                ex.getMessage()
        );

        return ResponseEntity
                .status(ex.getErrorCode().getHttpStatusCode())
                .body(List.of(errorDto));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorDTO>> handleValidationException(MethodArgumentNotValidException ex) {
        List<ErrorDTO> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorDTO(
                        "VALIDATION_ERROR",
                        String.format("Champ '%s' : %s", error.getField(), error.getDefaultMessage())
                ))
                .toList();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<List<ErrorDTO>> handleNotFound(NoHandlerFoundException ex) {
        var errorDto = new ErrorDTO(
                "NOT_FOUND",
                String.format("La route '%s' n'existe pas", ex.getRequestURL())
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(List.of(errorDto));
    }
}
