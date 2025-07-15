package com.courses.hexagonalapi.internship.domain.defenses;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DefenseRepositoryFetcher {
    List<Defense> findAll();
    Optional<Defense> findById(UUID defenseId);
}
