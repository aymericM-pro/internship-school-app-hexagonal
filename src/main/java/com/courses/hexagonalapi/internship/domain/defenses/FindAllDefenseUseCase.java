package com.courses.hexagonalapi.internship.domain.defenses;

import com.courses.hexagonalapi.core.BusinessException;

import java.util.List;

public class FindAllDefenseUseCase {

    private final DefenseRepositoryFetcher defenseRepositoryFetcher;

    public FindAllDefenseUseCase(DefenseRepositoryFetcher defenseRepositoryFetcher) {
        this.defenseRepositoryFetcher = defenseRepositoryFetcher;
    }

    public List<Defense> findAllDefenses() {
        List<Defense> defenses = defenseRepositoryFetcher.findAll();

        if (defenses.isEmpty()) {
            throw new BusinessException(
                    ErrorDefenseCode.DEFENSE_NOT_FOUND,
                    "Aucune soutenance trouvée"
            );
        }

        return defenses;
    }
}
