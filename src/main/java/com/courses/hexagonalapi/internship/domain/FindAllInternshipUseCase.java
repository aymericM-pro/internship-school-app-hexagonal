package com.courses.hexagonalapi.internship.domain;

import java.util.List;

public class FindAllInternshipUseCase {

    private final InternshipRepositoryFetcher internshipRepositoryFetcher;

    public FindAllInternshipUseCase(InternshipRepositoryFetcher internshipRepositoryFetcher) {
        this.internshipRepositoryFetcher = internshipRepositoryFetcher;
    }

    public List<Internship> findAll() {
        return this.internshipRepositoryFetcher.findAll();
    }
}
