package com.courses.hexagonalapi.internship.domain.internships;

import com.courses.hexagonalapi.core.ErrorCode;

public enum ErrorInternshipCode implements ErrorCode {
    INTERNSHIP_NOT_FOUND(404),
    INVALID_INTERNSHIP_DATA(400),
    POSTGRES_SAVE_ERROR(500),
    POSTGRES_FIND_ERROR(500);

    private final int httpStatusCode;

    ErrorInternshipCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }
}
