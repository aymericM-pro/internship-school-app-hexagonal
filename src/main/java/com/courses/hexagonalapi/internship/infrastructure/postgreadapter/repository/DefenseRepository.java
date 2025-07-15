package com.courses.hexagonalapi.internship.infrastructure.postgreadapter.repository;

import com.courses.hexagonalapi.internship.infrastructure.postgreadapter.entity.DefenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DefenseRepository extends JpaRepository<DefenseEntity, UUID> {
}
