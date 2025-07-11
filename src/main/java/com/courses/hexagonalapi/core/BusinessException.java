package com.courses.hexagonalapi.core;

import com.courses.hexagonalapi.internship.domain.ErrorInternshipCode;

public class BusinessException extends RuntimeException {

    private final ErrorInternshipCode errorCode;

    public BusinessException(ErrorInternshipCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorInternshipCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ErrorInternshipCode getErrorCode() {
        return errorCode;
    }
}