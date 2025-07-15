package com.courses.hexagonalapi.internship.domain.internships;

import com.courses.hexagonalapi.core.BusinessException;

import java.util.List;

public class FindAllInternshipUseCase {

    private final InternshipRepositoryFetcher internshipRepositoryFetcher;

    public FindAllInternshipUseCase(InternshipRepositoryFetcher internshipRepositoryFetcher) {
        this.internshipRepositoryFetcher = internshipRepositoryFetcher;
    }

    public List<Internship> findAll() {
        List<Internship> internships = internshipRepositoryFetcher.findAll();

        if (internships.isEmpty()) {
            throw new BusinessException(ErrorInternshipCode.INTERNSHIP_NOT_FOUND, "Aucun stage trouvé");
        }

        return internships;
    }
}
