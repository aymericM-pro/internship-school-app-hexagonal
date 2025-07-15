package com.courses.hexagonalapi.internship.domain.defenses;

import com.courses.hexagonalapi.internship.application.DefenseRequest;

import java.util.UUID;

public class SubmitDefenseUseCase {

    private final DefenseRepositorySaver defenseRepositorySaver;

    public SubmitDefenseUseCase(DefenseRepositorySaver defenseRepositorySaver) {
        this.defenseRepositorySaver = defenseRepositorySaver;
    }

    public Defense saveDefense(DefenseRequest request) {
        Defense defense = new Defense(
                UUID.randomUUID(),
                request.internshipId(),
                request.preferredDate(),
                request.preferredTime(),
                request.presentationTitle(),
                request.comments(),
                DefenseStatus.PENDING_REQUEST
        );

        defenseRepositorySaver.save(defense);
        return defense;
    }
}
