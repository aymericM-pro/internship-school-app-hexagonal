package com.courses.hexagonalapi.internship.infrastructure.postgreadapter.mapper;

import com.courses.hexagonalapi.internship.domain.internships.Internship;
import com.courses.hexagonalapi.internship.infrastructure.postgreadapter.entity.InternshipEntity;

public interface InternshipEntityMapper {

    static Internship fromEntityToDomain(InternshipEntity internship) {
        return new Internship(
                internship.getInternshipId(),
                internship.getTitle(),
                internship.getStartDate(),
                internship.getEndDate(),
                internship.getStatus(),
                internship.getDefense() != null ? DefenseEntityMapper.fromEntityToDomain(internship.getDefense()) : null
        );
    }

    static InternshipEntity fromDomainToEntity(Internship internship) {
        return new InternshipEntity(
                internship.internshipId(),
                internship.title(),
                internship.startDate(),
                internship.endDate(),
                internship.status()
        );
    }
}
