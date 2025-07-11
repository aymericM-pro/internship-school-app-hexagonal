package com.courses.hexagonalapi.internship.domain;

import com.courses.hexagonalapi.core.BusinessException;
import com.courses.hexagonalapi.internship.application.SubmitInternshipRequest;

import java.util.UUID;

public class SubmitInternshipUseCase {

    private final InternshipRepositorySaver internshipRepositorySaver;

    public SubmitInternshipUseCase(InternshipRepositorySaver internshipRepositorySaver) {
        this.internshipRepositorySaver = internshipRepositorySaver;
    }

    public Internship save(SubmitInternshipRequest request) {
        if (request.startDate().isAfter(request.endDate())) {
            throw new BusinessException(
                    ErrorInternshipCode.INVALID_INTERNSHIP_DATA,
                    "La date de début ne peut pas être après la date de fin"
            );
        }

        Internship internship = new Internship(
                UUID.randomUUID(),
                request.title(),
                request.startDate(),
                request.endDate(),
                InternshipStatus.SUBMITTED
        );

        internshipRepositorySaver.save(internship);
        return internship;
    }
}
