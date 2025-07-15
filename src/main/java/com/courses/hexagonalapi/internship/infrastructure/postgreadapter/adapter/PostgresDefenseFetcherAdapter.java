package com.courses.hexagonalapi.internship.infrastructure.postgreadapter.adapter;

import com.courses.hexagonalapi.core.BusinessException;
import com.courses.hexagonalapi.internship.domain.defenses.Defense;
import com.courses.hexagonalapi.internship.domain.defenses.DefenseRepositoryFetcher;
import com.courses.hexagonalapi.internship.domain.defenses.ErrorDefenseCode;
import com.courses.hexagonalapi.internship.domain.internships.ErrorInternshipCode;
import com.courses.hexagonalapi.internship.infrastructure.postgreadapter.mapper.DefenseEntityMapper;
import com.courses.hexagonalapi.internship.infrastructure.postgreadapter.mapper.InternshipEntityMapper;
import com.courses.hexagonalapi.internship.infrastructure.postgreadapter.repository.DefenseRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PostgresDefenseFetcherAdapter implements DefenseRepositoryFetcher {

    public DefenseRepository defenseRepository;

    public PostgresDefenseFetcherAdapter(DefenseRepository defenseRepository) {
        this.defenseRepository = defenseRepository;
    }

    @Override
    public List<Defense> findAll() {
       try {
           return defenseRepository.findAll().stream()
                   .map(DefenseEntityMapper::fromEntityToDomain)
                   .toList();
       } catch (Exception e) {
           final String message = "Erreur lors de la récupération des défenses";
           throw new BusinessException(ErrorDefenseCode.POSTGRES_FIND_ERROR, message, e);
       }
    }

    @Override
    public Optional<Defense> findById(UUID defenseId) {
        try {
            return defenseRepository.findById(defenseId).map(DefenseEntityMapper::fromEntityToDomain);
        }catch (Exception e) {
            final String message = String.format("Erreur lors de la récupération de la défense avec l'ID '%s'", defenseId);
            throw new BusinessException(ErrorInternshipCode.POSTGRES_FIND_ERROR, message, e);
        }
    }
}
