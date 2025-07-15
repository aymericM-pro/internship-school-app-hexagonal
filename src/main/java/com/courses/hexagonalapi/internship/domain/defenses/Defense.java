package com.courses.hexagonalapi.internship.domain.defenses;

import java.time.LocalDate;
import java.util.UUID;

public record Defense(
        UUID defenseId,
        UUID internshipId,
        LocalDate preferredDate,
        String preferredTime,
        String presentationTitle,
        String comments,
        DefenseStatus status) {
}
