package com.courses.hexagonalapi.internship.domain.internships;

import com.courses.hexagonalapi.internship.domain.defenses.Defense;

import java.time.LocalDate;
import java.util.UUID;

public record Internship(
        UUID internshipId,
        String title,
        LocalDate startDate,
        LocalDate endDate,
        InternshipStatus status,
        Defense defense) {
}
