package com.courses.hexagonalapi.internship.infrastructure.postgreadapter.repository;

import com.courses.hexagonalapi.internship.infrastructure.postgreadapter.entity.InternshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InternshipRepository extends JpaRepository<InternshipEntity, UUID> {

}
