package com.courses.hexagonalapi.internship.infrastructure.postgreadapter.mapper;

import com.courses.hexagonalapi.internship.domain.defenses.Defense;
import com.courses.hexagonalapi.internship.infrastructure.postgreadapter.entity.DefenseEntity;
import com.courses.hexagonalapi.internship.infrastructure.postgreadapter.entity.InternshipEntity;

public interface DefenseEntityMapper {

    static Defense fromEntityToDomain(DefenseEntity defense) {
        return new Defense(
                defense.getDefenseId(),
                defense.getInternship().getInternshipId(),
                defense.getPreferredDate(),
                defense.getPreferredTime(),
                defense.getPresentationTitle(),
                defense.getComments(),
                defense.getStatus()
        );
    }

    static DefenseEntity fromDomainToEntity(Defense defense, InternshipEntity internship) {
        return new DefenseEntity(
                defense.defenseId(),
                defense.preferredDate(),
                defense.preferredTime(),
                defense.presentationTitle(),
                defense.comments(),
                defense.status(),
                internship
        );
    }
}

