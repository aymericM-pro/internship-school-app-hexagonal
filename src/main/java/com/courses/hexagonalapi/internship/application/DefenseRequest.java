package com.courses.hexagonalapi.internship.application;

import java.time.LocalDate;
import java.util.UUID;

public record DefenseRequest(
        UUID internshipId,
        LocalDate preferredDate,
        String preferredTime,
        String presentationTitle,
        String comments
) {
}
