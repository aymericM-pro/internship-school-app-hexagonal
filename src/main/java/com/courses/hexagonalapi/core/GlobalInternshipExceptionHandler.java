package com.courses.hexagonalapi.core;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
public class GlobalInternshipExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorDTO>> handleInternshipException(BusinessException ex) {
        var errorDto = new ErrorDTO(
                ex.getErrorCode().toString(),
                ex.getMessage()
        );
        return ResponseEntity
                .status(ex.getErrorCode().getHttpStatusCode())
                .body(List.of(errorDto));
    }
}
