package com.courses.hexagonalapi.internship.domain.defenses;

import com.courses.hexagonalapi.core.BusinessException;

import java.util.UUID;

public class FindByIdDefenseUseCase {

    public DefenseRepositoryFetcher defenseRepositoryFetcher;

    public FindByIdDefenseUseCase(DefenseRepositoryFetcher defenseRepositoryFetcher) {
        this.defenseRepositoryFetcher = defenseRepositoryFetcher;
    }

    public Defense findDefenseById(UUID defenseId) {
        return defenseRepositoryFetcher.findById(defenseId)
                .orElseThrow(() -> new BusinessException(
                        ErrorDefenseCode.DEFENSE_NOT_FOUND,
                        "Soutenance introuvable pour l'ID : " + defenseId
                ));
    }
}
