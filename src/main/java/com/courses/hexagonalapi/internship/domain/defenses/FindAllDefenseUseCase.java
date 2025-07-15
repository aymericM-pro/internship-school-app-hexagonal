package com.courses.hexagonalapi.internship.domain.defenses;

import java.util.List;

public class FindAllDefenseUseCase {

    public DefenseRepositoryFetcher defenseRepositoryFetcher;

    public FindAllDefenseUseCase(DefenseRepositoryFetcher defenseRepositoryFetcher) {
        this.defenseRepositoryFetcher = defenseRepositoryFetcher;
    }

    public List<Defense> findAllDefenses() {
        return defenseRepositoryFetcher.findAll();
    }
}
