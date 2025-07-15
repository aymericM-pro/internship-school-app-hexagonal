package com.courses.hexagonalapi.internship.infrastructure.postgreadapter.adapter;

import com.courses.hexagonalapi.core.BusinessException;
import com.courses.hexagonalapi.internship.domain.defenses.Defense;
import com.courses.hexagonalapi.internship.domain.defenses.DefenseRepositorySaver;
import com.courses.hexagonalapi.internship.domain.defenses.ErrorDefenseCode;
import com.courses.hexagonalapi.internship.infrastructure.postgreadapter.mapper.DefenseEntityMapper;
import com.courses.hexagonalapi.internship.infrastructure.postgreadapter.repository.DefenseRepository;
import com.courses.hexagonalapi.internship.infrastructure.postgreadapter.repository.InternshipRepository;
import org.springframework.stereotype.Component;

@Component
public class PostgresDefenseSaverAdapter implements DefenseRepositorySaver {
    private final DefenseRepository defenseRepository;
    private final InternshipRepository internshipRepository;

    public PostgresDefenseSaverAdapter(
            DefenseRepository defenseRepository,
            InternshipRepository internshipRepository
    ) {
        this.defenseRepository = defenseRepository;
        this.internshipRepository = internshipRepository;
    }

    @Override
    public void save(Defense defense) {
        try {
            var internship = internshipRepository.findById(defense.internshipId())
                    .orElseThrow(() -> new BusinessException(
                            ErrorDefenseCode.POSTGRES_FIND_ERROR,
                            "Stage non trouvé pour cette soutenance"
                    ));

            var defenseEntity = DefenseEntityMapper.fromDomainToEntity(defense, internship);
            defenseRepository.save(defenseEntity);

        } catch (Exception e) {
            throw new BusinessException(
                    ErrorDefenseCode.POSTGRES_SAVE_ERROR,
                    "Erreur lors de la sauvegarde de la défense",
                    e
            );
        }
    }
}
