package com.courses.hexagonalapi.internship.domain.defenses;

import com.courses.hexagonalapi.core.ErrorCode;

public enum ErrorDefenseCode implements ErrorCode {
    DEFENSE_NOT_FOUND(404),
    DEFENSE_ALREADY_EXISTS(400),
    INVALID_DEFENSE_DATA(400),
    POSTGRES_SAVE_ERROR(500),
    POSTGRES_FIND_ERROR(500);

    private final int httpStatusCode;

    ErrorDefenseCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    @Override
    public int getHttpStatusCode() {
        return httpStatusCode;
    }
}
