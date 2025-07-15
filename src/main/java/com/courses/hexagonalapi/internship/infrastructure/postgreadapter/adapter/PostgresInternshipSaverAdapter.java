package com.courses.hexagonalapi.internship.infrastructure.postgreadapter.adapter;

import com.courses.hexagonalapi.core.BusinessException;
import com.courses.hexagonalapi.internship.domain.internships.ErrorInternshipCode;
import com.courses.hexagonalapi.internship.domain.internships.Internship;
import com.courses.hexagonalapi.internship.domain.internships.InternshipRepositorySaver;
import com.courses.hexagonalapi.internship.infrastructure.postgreadapter.mapper.InternshipEntityMapper;
import com.courses.hexagonalapi.internship.infrastructure.postgreadapter.repository.InternshipRepository;
import org.springframework.stereotype.Component;

@Component
public class PostgresInternshipSaverAdapter implements InternshipRepositorySaver {
    private final InternshipRepository internshipRepository;

    public PostgresInternshipSaverAdapter(InternshipRepository internshipRepository) {
        this.internshipRepository = internshipRepository;
    }

    @Override
    public void save(Internship internship) {
        try {
            internshipRepository.save(InternshipEntityMapper.fromDomainToEntity(internship));
        } catch (Exception e) {
            throw new BusinessException(
                    ErrorInternshipCode.POSTGRES_SAVE_ERROR,
                    "Erreur lors de la sauvegarde du stage",
                    e
            );
        }
    }
}
