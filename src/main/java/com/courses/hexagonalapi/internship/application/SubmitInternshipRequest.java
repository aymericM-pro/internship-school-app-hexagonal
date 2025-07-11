package com.courses.hexagonalapi.internship.application;

import java.time.LocalDate;

public record SubmitInternshipRequest(
        String title,
        LocalDate startDate,
        LocalDate endDate
) {}
