package com.courses.hexagonalapi.internship.infrastructure.postgreadapter.adapter;

import com.courses.hexagonalapi.core.BusinessException;
import com.courses.hexagonalapi.internship.domain.internships.ErrorInternshipCode;
import com.courses.hexagonalapi.internship.domain.internships.Internship;
import com.courses.hexagonalapi.internship.domain.internships.InternshipRepositoryFetcher;
import com.courses.hexagonalapi.internship.infrastructure.postgreadapter.mapper.InternshipEntityMapper;
import com.courses.hexagonalapi.internship.infrastructure.postgreadapter.repository.InternshipRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PostgresInternshipFetcherAdapter implements InternshipRepositoryFetcher {

    private final InternshipRepository internshipRepository;

    public PostgresInternshipFetcherAdapter(InternshipRepository internshipRepository) {
        this.internshipRepository = internshipRepository;
    }

    @Override
    public Optional<Internship> findById(UUID internshipId) {
        try {
            return internshipRepository.findById(internshipId)
                    .map(InternshipEntityMapper::fromEntityToDomain);
        } catch (Exception e) {
            final String message = String.format("Erreur lors de la récupération du stage avec l'ID '%s'", internshipId);
            throw new BusinessException(ErrorInternshipCode.POSTGRES_FIND_ERROR, message, e);
        }
    }

    @Override
    public List<Internship> findAll() {
        try {
            return internshipRepository.findAll()
                    .stream()
                    .map(InternshipEntityMapper::fromEntityToDomain)
                    .toList();
        } catch (Exception e) {
            final String message = "Erreur lors de la récupération de la liste des stages depuis PostgreSQL";
            throw new BusinessException(ErrorInternshipCode.POSTGRES_FIND_ERROR, message, e);
        }
    }
}
