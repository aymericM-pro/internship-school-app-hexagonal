package com.courses.hexagonalapi.internship.domain.internships;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InternshipRepositoryFetcher {
    Optional<Internship> findById(UUID internshipId);
    List<Internship> findAll();
}
