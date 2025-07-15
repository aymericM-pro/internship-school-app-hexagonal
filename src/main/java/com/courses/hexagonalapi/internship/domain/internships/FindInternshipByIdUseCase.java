package com.courses.hexagonalapi.internship.domain.internships;

import com.courses.hexagonalapi.core.BusinessException;

import java.util.UUID;

public class FindInternshipByIdUseCase {

    private final InternshipRepositoryFetcher internshipRepositoryFetcher;

    public FindInternshipByIdUseCase(InternshipRepositoryFetcher internshipRepositoryFetcher) {
        this.internshipRepositoryFetcher = internshipRepositoryFetcher;
    }

    public Internship findById(UUID internshipId) {
        return internshipRepositoryFetcher.findById(internshipId)
                .orElseThrow(() -> new BusinessException(ErrorInternshipCode
                        .INTERNSHIP_NOT_FOUND,
                        "Stage introuvable avec l’ID : " + internshipId
                ));
    }
}
